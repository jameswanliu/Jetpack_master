package com.stephen.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;


import java.util.Locale;

public class MultiLanguageUtil {

    public static final int LANGUAGE_FOLLOW_SYSTEM = 0; //跟随系统
    public static final int LANGUAGE_EN = 2;    //英文
    public static final int LANGUAGE_CHINESE_SIMPLIFIED = 1; //简体

    private static final String SHARED_PATH = "app_info";
    private SharedPreferences sharedPreferences;

    private static final String TAG = "MultiLanguageUtil";
    private static MultiLanguageUtil instance;
    private Context mContext;
    public static final String SAVE_LANGUAGE = "save_language";

    public static void init(Context mContext) {
        if (instance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageUtil(mContext);
                }
            }
        }
    }

    public static MultiLanguageUtil getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You must be init MultiLanguageUtil first");
        }
        return instance;
    }

    private MultiLanguageUtil(Context context) {
        this.mContext = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
    }

    /**
     * 设置语言
     */
    public boolean setConfiguration() {
        Locale targetLocale = getLanguageLocale();
        Configuration configuration = mContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale);
        } else {
            configuration.locale = targetLocale;
        }

        Log.i(TAG, "locale=" + targetLocale.getDisplayLanguage());
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
        return true;
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回简体中文
     *
     * @return
     */
    private Locale getLanguageLocale() {
        int languageType = getInt(MultiLanguageUtil.SAVE_LANGUAGE, 0);
        Log.i(TAG, "languageType= " + languageType);
        if (languageType == LANGUAGE_FOLLOW_SYSTEM) {
            Locale sysLocale = getSysLocale();
            return sysLocale;
        } else if (languageType == LANGUAGE_EN) {
            Settings.INSTANCE.setLanguage("en");
            return Locale.ENGLISH;
        } else if (languageType == LANGUAGE_CHINESE_SIMPLIFIED) {
            Settings.INSTANCE.setLanguage("cn");
            return Locale.SIMPLIFIED_CHINESE;
        }
        getSystemLanguage(getSysLocale());
        return Locale.SIMPLIFIED_CHINESE;
    }

    private String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();

    }

    //以上获取方式需要特殊处理一下
    public Locale getSysLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 更新语言
     *
     * @param languageType
     */
    public boolean updateLanguage(int languageType) {
        putInt(MultiLanguageUtil.SAVE_LANGUAGE, languageType);
       return MultiLanguageUtil.getInstance().setConfiguration();
    }

//    public String getLanguageName(Context context) {
//        int languageType = CommSharedUtil.getInstance(context).getInt(MultiLanguageUtil.SAVE_LANGUAGE,LANGUAGE_FOLLOW_SYSTEM);
//        if (languageType == LANGUAGE_EN) {
//            return mContext.getString(R.string.setting_language_english);
//        } else if (languageType == LANGUAGE_CHINESE_SIMPLIFIED) {
//            return mContext.getString(R.string.setting_simplified_chinese);
//        }
//        return mContext.getString(R.string.setting_language_auto);
//    }

//    /**
//     * 获取到用户保存的语言类型
//     *
//     * @return
//     */
//    public int getLanguageType() {
//        int languageType = getInt(MultiLanguageUtil.SAVE_LANGUAGE, LANGUAGE_FOLLOW_SYSTEM);
//        if (languageType == LANGUAGE_CHINESE_SIMPLIFIED) {
//            return LANGUAGE_CHINESE_SIMPLIFIED;
//        } else if (languageType == LANGUAGE_EN) {
//            return LANGUAGE_EN;
//        } else if (languageType == LANGUAGE_FOLLOW_SYSTEM) {
//            return LANGUAGE_FOLLOW_SYSTEM;
//        }
//        Log.e(TAG, "getLanguageType" + languageType);
//        return languageType;
//    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context);
        } else {
            MultiLanguageUtil.getInstance().setConfiguration();
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getInstance().getLanguageLocale();
        configuration.setLocale(locale);
        Log.e(TAG, "createConfigurationResources locale" + locale.getDisplayCountry());
        return context.createConfigurationContext(configuration);
    }


    public void putInt(String key, int value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }


    public void putString(String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }


    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }


    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }


    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public void remove(String key) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

}
