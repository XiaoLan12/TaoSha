package com.yizhisha.taosha.ui.home.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.common.flowlayout.FlowLayout;
import com.yizhisha.taosha.common.flowlayout.TagAdapter;
import com.yizhisha.taosha.common.flowlayout.TagFlowLayout;
import com.yizhisha.taosha.utils.xunfei.IatSettings;
import com.yizhisha.taosha.utils.xunfei.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/1.
 */

public class SearchActivity  extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tagflowlayout)
    TagFlowLayout tagFlowLayout;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.search_et)
    EditText searchEt;

    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private SharedPreferences mSharedPreferences;

    private Toast mToast;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    private boolean mTranslateEnable = false;
    private static String TAG = SearchActivity.class.getSimpleName();

    private TagAdapter<String> maAdapter;

    private String[] data=new String[]{"棉","麻","丝","毛","仿","兔","貂","羊","牦","绒","粘","尼","氨","腈","涤","马海","羊驼","天丝","金银","100%","段染",
     "彩点","竹节","高捻","冰","色纺","喷毛","圈圈","人造","爽","锦纶","亚麻",
     "包芯","纤维","欧根","莫代尔","莱赛尔","抗起球","AB","美丽奴","巴素兰","安哥拉",
     "拉斐尔","包缠","带子","经纬","花式","特种"};
    private List<String> dataList = new ArrayList<>();
    private StringBuilder value=new StringBuilder();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolBar() {
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(SearchActivity.this, this.getResources().getColor(R.color.red1));
    }
    @Override
    protected void initView() {
        final LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loadData();
        tagFlowLayout.setAdapter(maAdapter=new TagAdapter<String>(dataList) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) layoutInflater.inflate(R.layout.tagflowlayout,
                        tagFlowLayout, false);
                tv.setText(str);
                return tv;
            }
        });

        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if(selectPosSet.size()>0){
                    value.setLength(0);
                    for(int i:selectPosSet){
                        value.append(maAdapter.getItem(i));
                        value.append(" ");
                    }
                }else{
                    value.setLength(0);
                }
                searchEt.setText(value.toString());
            }
        });
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                searchEt.setSelection(searchEt.getText().length());
            }
        });
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(SearchActivity.this, mInitListener);
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(SearchActivity.this, mInitListener);
        mToast = Toast.makeText(SearchActivity.this, "", Toast.LENGTH_SHORT);
        mSharedPreferences = SearchActivity.this.getSharedPreferences(IatSettings.PREFER_NAME,
                Activity.MODE_PRIVATE);
    }
    private void loadData(){
        for(String str:data){
            dataList.add(str);
        }
    }
    //扫描二维码
    //https://cli.im/text?2dd0d2b267ea882d797f03abf5b97d88二维码生成网站
    public void scan() {
        // 扫描功能
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //申请CAMERA权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 3);
        } else {

            Intent intent=  new Intent(this, CaptureActivity.class);
            startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
//                searchEt.setText(result);

                if(result.contains("id/")){
                    int i=result.indexOf("id/");
                    try{
                        Bundle bundle1=new Bundle();
                        bundle1.putInt("TYPE",1);
                        bundle1.putInt("id",Integer.parseInt(result.substring(i+3,result.length())));
                        startActivity(YarnActivity.class,bundle1);
                    }catch (Exception e){
                        searchEt.setText(result);
                    }

                }else{
                    searchEt.setText(result);
                }
            }
        }
    }
    @OnClick({R.id.img_back,R.id.search_tv,R.id.scan_iv,R.id.voice_iv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_back:
                finish_Activity(SearchActivity.this);
                break;
            case R.id.search_tv:
                Bundle bundle = new Bundle();
                bundle.putString("KEY", searchEt.getText().toString().trim());
                startActivity(SelectYarnActivity.class, bundle);
                break;
            case R.id.scan_iv:
                scan();
                break;
            case R.id.voice_iv:
                //⑧申请录制音频的动态权限
                if (ContextCompat.checkSelfPermission(SearchActivity.this, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SearchActivity.this, new String[]{
                            android.Manifest.permission.RECORD_AUDIO}, 1);
                } else {
                    startRecord();
                }
                break;
        }
    }
    int ret = 0; // 函数调用返回值
    private void startRecord(){

        // 移动数据分析，收集开始听写事件
        FlowerCollector.onEvent(SearchActivity.this, "iat_recognize");

        mIatResults.clear();
        // 设置参数
        setParam();
        setParam();
        boolean isShowDialog = mSharedPreferences.getBoolean(
                getString(R.string.pref_key_iat_show), true);
        if (isShowDialog) {
            // 显示听写对话框
            mIatDialog.setListener(mRecognizerDialogListener);
            mIatDialog.show();
            showTip(getString(R.string.text_begin));
        } else {
            // 不显示听写对话框
            ret = mIat.startListening(mRecognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                showTip("听写失败,错误码：" + ret);
            } else {
                showTip(getString(R.string.text_begin));
            }
        }
    }
    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

            if (isLast) {
                // TODO 最后的结果
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        searchEt.setText(resultBuffer.toString());
//        Toast.makeText(SelectYarnActivity.this,resultBuffer.toString(),Toast.LENGTH_SHORT).show();
//        mResultText.setText(resultBuffer.toString());
//        mResultText.setSelection(mResultText.length());
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

    };



    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 参数设置
     *
     * @paramparam
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        this.mTranslateEnable = mSharedPreferences.getBoolean( this.getString(R.string.pref_key_translate), false );
        if( mTranslateEnable ){
            Log.i( TAG, "translate enable" );
            mIat.setParameter( SpeechConstant.ASR_SCH, "1" );
            mIat.setParameter( SpeechConstant.ADD_CAP, "translate" );
            mIat.setParameter( SpeechConstant.TRS_SRC, "its" );
        }

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if (lag.equals("en_us")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
            mIat.setParameter(SpeechConstant.ACCENT, null);

            if( mTranslateEnable ){
                mIat.setParameter( SpeechConstant.ORI_LANG, "en" );
                mIat.setParameter( SpeechConstant.TRANS_LANG, "cn" );
            }
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);

            if( mTranslateEnable ){
                mIat.setParameter( SpeechConstant.ORI_LANG, "cn" );
                mIat.setParameter( SpeechConstant.TRANS_LANG, "en" );
            }
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "0"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }

    private void printTransResult (RecognizerResult results) {
        String trans  = JsonParser.parseTransResult(results.getResultString(),"dst");
        String oris = JsonParser.parseTransResult(results.getResultString(),"src");

        if( TextUtils.isEmpty(trans)|| TextUtils.isEmpty(oris) ){
            showTip( "解析结果失败，请确认是否已开通翻译功能。" );
        }else{
//            mResultText.setText( "原始语言:\n"+oris+"\n目标语言:\n"+trans );
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if( null != mIat ){
            // 退出时释放连接
            mIat.cancel();
            mIat.destroy();
        }
    }

    @Override
    public void onResume() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onResume(SearchActivity.this);
        FlowerCollector.onPageStart(TAG);
        super.onResume();

    }

    @Override
    public void onPause() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onPageEnd(TAG);
        FlowerCollector.onPause(SearchActivity.this);
        super.onPause();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            startRecord();
        }else {
            if (1 == requestCode) {
                Toast.makeText(SearchActivity.this,"用户拒绝了权限",Toast.LENGTH_SHORT).show();
            }

        }

        if (3 == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent=  new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,0);
            } else {
                // 未授权
            }
        }

    }
}
