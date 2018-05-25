package me.zuichu.staticlib.manager;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import me.zuichu.staticlib.config.StaticConfig;
import me.zuichu.staticlib.core.StaticApplication;
import me.zuichu.staticlib.utils.Utils;

/**
 * Created by office on 2018/4/13.
 * 崩溃信息的错误日志统计
 */

public class StaticCrash implements Thread.UncaughtExceptionHandler {
    private StaticCookie staticCookie = StaticConfig.staticCookie;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static StaticCrash INSTANCE = new StaticCrash();
    private Context context;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
    private String tips = "应用开小差了，稍后重启下，亲！";

    public StaticCookie getStaticCookie() {
        return staticCookie;
    }

    public void setStaticCookie(StaticCookie staticCookie) {
        this.staticCookie = staticCookie;
    }

    public static StaticCrash getInstance() {
        return INSTANCE;
    }


    public void init(Context context) {
        this.context = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                e.printStackTrace();
            }
            StaticApplication.finishAllSesion();
            //退出程序
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                throwable.printStackTrace();
                showToast(tips);
                Looper.loop();
            }
        }.start();
        saveCrash(throwable);
        return true;
    }

    private String saveCrash(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append(staticCookie.toString() + "\n");
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
//        if (BuildConfig.DEBUG) {
//            return null;
//        }
        /*
        这个 crashInfo 就是我们收集到的所有信息，可以做一个异常上报的接口用来提交用户的crash信息
         */
        String string = sb.toString();
        Utils.saveFile(string, StaticConfig.LOG_PATH);
        return null;
    }

    private void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
