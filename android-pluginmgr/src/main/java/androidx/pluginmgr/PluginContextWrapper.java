/**
 * 
 */
package androidx.pluginmgr;

import java.io.File;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

/**
 * Plugin Context 包装类
 * 
 * @author HouKangxi
 *
 */
class PluginContextWrapper extends ContextWrapper {
	private PlugInfo plugin;
	private static final String tag = "PluginContextWrapper";
	private ApplicationInfo applicationInfo;

	public PluginContextWrapper(Context base, PlugInfo plugin) {
		super(base);
		this.plugin = plugin;
		applicationInfo = new ApplicationInfo(
				plugin.getPackageInfo().applicationInfo);
		applicationInfo.sourceDir = plugin.getFilePath();
		applicationInfo.dataDir = ActivityOverider.getPluginBaseDir(
				plugin.getId()).getAbsolutePath();
	}

	@Override
	public File getFilesDir() {
		File dir = super.getFilesDir();
		Log.d(tag, "getFilesDir()=" + dir);
		return dir;
	}

	@Override
	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	@Override
	public Context getApplicationContext() {
		Log.d(tag, "getApplicationContext()");
		return this;
	}

	@Override
	public String getPackageName() {
		Log.d(tag, "getPackageName()");
		return plugin.getPackageName();
	}

	@Override
	public Resources getResources() {
		Log.d(tag, "getResources()");
		return plugin.getResources();
	}

	@Override
	public AssetManager getAssets() {
		Log.d(tag, "getAssets()");
		return plugin.getAssetManager();
	}
	// @Override
	// public Object getSystemService(String name) {
	// if (name.equals(Context.ACTIVITY_SERVICE)) {
	// if (plugin.getApplicationInfo().process != null) {
	// return plugin.activityManager;
	// }
	// }
	// return super.getSystemService(name);
	// }
}
