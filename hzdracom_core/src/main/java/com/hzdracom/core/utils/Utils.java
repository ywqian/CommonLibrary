package com.hzdracom.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.FloatMath;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.hzdracom.core.R;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
	private static  WindowManager windowManager;
	private static int width,heih;
	private static Intent intent;
	private static final String intentSpace = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	public static String filterName(String path) {
		String result = path;
		int index = path.lastIndexOf(File.separator);
		if (index != -1)
		{
			result = path.substring(index + 1);
		}
		index = result.lastIndexOf(".");
		if (index != -1)
		{
			result = result.substring(0, index);
		}
		return result;
	}

	/**
	 * @Title: getpercent
	 * @Description: 计算百分比
	 * @param @param index
	 * @param @param count
	 * @param @param minimum小数点后面的位数
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2012-2-17 上午10:07:24
	 */
	public static String getPercent(long index, long count, int minimum) {
		if (count == 0) { return "0%"; }
		DecimalFormat format = new DecimalFormat("###,##0");
		format.setMinimumFractionDigits(minimum);
		double per = ((double) index / count) * 100;
		return format.format(per);
	}

	public static String filterExt(String path) {
		String result = "";
		int index = path.lastIndexOf(".");
		if (index != -1)
		{
			result = path.substring(index + 1);
		}
		return result.toLowerCase();
	}

	//	public static SharedPreferences sharePreferences(Context context){
	//		SharedPreferences sp = context.getSharedPreferences(Const.SP_DATA_NAME, Context.MODE_PRIVATE);
	//		return sp;
	//	}
	//	
	private static Toast sToast;

	public static void ToastShow(Context context, CharSequence charSequence) {
		if (sToast == null) sToast = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT);
		else sToast.setText(charSequence);
		sToast.show();
	}

	public static void ToastShow(Context context, int id) {
		if (sToast == null) sToast = Toast.makeText(context, id, Toast.LENGTH_SHORT);
		else sToast.setText(id);
		sToast.show();
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String getFormatDate(long time) {
		return sdf.format(new Date(time));
	}

	public static Date getStringtoDate(String strTime) {
		Date date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(strTime);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	private static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

	public static String getFormatTime(long time) {
		return sdf2.format(new Date(time));
	}
	public static String getFormatTime(Date time) {
		return sdf2.format(time);
	}

	private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");

	public static String getAccountFormatTime(long time) {
		return sdf3.format(new Date(time));
	}

	static DecimalFormat df2 = new DecimalFormat("#0.00");

	public static String getPercent(long cur, long len) {
		float fPercent = (float) (cur * 1.0 / len);
		return df2.format(fPercent * 100);
	}

	/**
	 * 获取指定格式的时间
	 *
	 * @param format
	 *            自定义格式,例：yyyy-MM-dd hh:mm:ss
	 * @param time
	 *            时间(毫秒)
	 * @return
	 */
	public static String getFormatTime(String format, long time) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String result = df.format(new Date(time));
		return result;
	}

	/**
	 * 计算文件的大小
	 *
	 * @param filesize
	 * @return
	 */
	public static String getFileSizeString(long filesize) {
		String sizeString = null;
		float size = 0;
		if (filesize < 1024)
		{
			//xx b
			size = filesize;
			sizeString = df2.format(size) + " B";
		}
		else if (filesize < 1024 * 1024)
		{
			//xx k
			size = (float) (filesize*1.0f / 1024);
			sizeString = df2.format(size) + " KB";
		}
		else
		{
			//xx m
			size = (float) (filesize*1.0f / (1024 * 1024));
			sizeString = df2.format(size) + " MB";
		}
		return sizeString;
	}

	public static String getString(String str, int maxsize) {
		if (str.length() > maxsize)
		{
			str = str.substring(0, maxsize);
		}
		return str;
	}

	public static String removeNameSpace(String ns_tag) {
		String[] ns__tag = ns_tag.split(":");
		return ns__tag[ns__tag.length - 1];
	}

	public static void showToast(Context mContext, int resId) {
		Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context mContext, String text) {
		if(null!= mContext && !TextUtils.isEmpty(text)){
			Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 判断String为空
	 *
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return (string == null || "".equalsIgnoreCase(string.trim()) || "null".equals(string));
	}

	/**
	 * 判断String不为空
	 *
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return string != null && string.trim().length() > 0;
	}

	public static final byte              SCAN_TYPE_ALL      = 0;
	public static final byte              SCAN_TYPE_TXT      = 1;
	public static final byte              SCAN_TYPE_EPUB     = 2;
	public static final byte              SCAN_TYPE_PDF      = 3;
	public static final byte              SCAN_TYPE_OFFICE   = 4;
	public static final byte              SCAN_TYPE_OTHER    = 5;

	public static final ArrayList<String> readFileTypeAll    = new ArrayList<String>();
	public static final ArrayList<String> readFileTypeTxt    = new ArrayList<String>();
	public static final ArrayList<String> readFileTypeEpub   = new ArrayList<String>();
	public static final ArrayList<String> readFileTypePdf    = new ArrayList<String>();
	public static final ArrayList<String> readFileTypeOffice = new ArrayList<String>();
	public static final ArrayList<String> readFileTypeOther  = new ArrayList<String>();
	static
	{
		/** txt */
		readFileTypeTxt.add("txt");

		readFileTypeEpub.add("epub");

		//		readFileTypePdf.add("pdf");
		/** office */
		readFileTypeOffice.add("doc");
		//		readFileTypeOffice.add("xls");
		//		readFileTypeOffice.add("ppt");
		readFileTypeOffice.add("docx");
		//		readFileTypeOffice.add("xlsx");
		//		readFileTypeOffice.add("pptx");
		/** other */
		//		readFileTypeOther.add("rtf");
		//		readFileTypeOther.add("umd");

		//		readFileTypeOther.add("log");

		readFileTypeAll.addAll(readFileTypeTxt);
		readFileTypeAll.addAll(readFileTypeEpub);
		//		readFileTypeAll.addAll(readFileTypePdf);
		readFileTypeAll.addAll(readFileTypeOffice);
		//		readFileTypeAll.addAll(readFileTypeOther);
	}

	public static boolean isReaderFileType(File file) {
		if (file != null && file.exists() && file.isFile() && file.getPath().indexOf(".") != -1)
		{
			String path = file.getPath();
			String type = path.substring(path.lastIndexOf(".") + 1, path.length());
			return readFileTypeAll.contains(type);
		}
		return false;
	}

	public static byte getType(File file) {
		if (file != null && file.exists() && file.isFile() && file.getPath().indexOf(".") != -1)
		{
			String path = file.getPath();
			String type = path.substring(path.lastIndexOf(".") + 1, path.length());
			if (readFileTypeTxt.contains(type)) return SCAN_TYPE_TXT;
			else if (readFileTypeEpub.contains(type)) return SCAN_TYPE_EPUB;
			else if (readFileTypePdf.contains(type)) return SCAN_TYPE_PDF;
			else if (readFileTypeOffice.contains(type)) return SCAN_TYPE_OFFICE;
			else if (readFileTypeOther.contains(type)) return SCAN_TYPE_OTHER;
			else return SCAN_TYPE_ALL;
		}
		return SCAN_TYPE_ALL;
	}

	public static String getProcess(double paycount) {
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(paycount) + "%";
	}

	/**
	 * bitmap转成byte[] 获得限制大小的图片JPEG数据 (用于头像)
	 *
	 * @param bitmap
	 *            图片
	 * @param quality
	 *            起始jpeg图片质量
	 * @param limitSize
	 *            限制的图片大小
	 */
	public static byte[] getImageDataByLimitSize(Bitmap bitmap, int quality, int limitSize) {
		if (bitmap == null || limitSize <= 0) return null;

		if (quality <= 0) { return null; }

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, quality, baos);
		byte[] bytes = baos.toByteArray();

		if (bytes.length > limitSize)
		{
			bytes = null;
			bytes = getImageDataByLimitSize(bitmap, quality - 10, limitSize);
		}

		return bytes;
	}

	/**
	 *  根据照片路径 把照片转成 Bitmap
	 * @param pathName
	 * @param maxArea
	 * @param isChanged
	 * @return
	 * @throws FileNotFoundException
	 */

	public static Bitmap decodeFile(String pathName, int maxArea, AtomicBoolean isChanged) throws FileNotFoundException {
		Options options = decodeBounds(pathName);
		Options opts = new Options();
		opts.inSampleSize = computeShampleSize(options.outWidth, options.outHeight, maxArea);

		//		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		FileInputStream is = new FileInputStream(pathName);
		Bitmap bitmap = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), opts);
		if (bitmap == null) { return null; }

		int inSampleSize = options.outWidth / bitmap.getWidth(); // 实际的缩放倍数可能跟设置的缩放倍数有所不同
		if (inSampleSize > 1)
		{
			isChanged.set(true);
		}

		try
		{
			ExifInterface exitInterface = new ExifInterface(pathName);
			int orientation = exitInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			boolean shouldProcess = false;
			Matrix matrix = new Matrix();
			if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
			{
				shouldProcess = true;
				matrix.setRotate(90);
			}
			else if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
			{
				shouldProcess = true;
				matrix.setRotate(180);
			}
			else if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
			{
				shouldProcess = true;
				matrix.setRotate(270);
			}
			if (shouldProcess)
			{
				Bitmap bitmapSrc = bitmap;
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

				bitmapSrc.recycle();
				bitmapSrc = null;
				System.gc();

				isChanged.set(true);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bitmap;
	}


	public static Options decodeBounds(String pathName) {
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		return options;
	}

	public static int computeShampleSize(int width, int height, int maxArea) {
		if (width * height <= maxArea)
		{
			return 1;
		}
		else
		{
			return (int) FloatMath.ceil(FloatMath.sqrt(width * height / maxArea));
		}
	}

	/**
	 * 获取文件写入路径，无视错误
	 *
	 * @param fileName
	 *            文件全名
	 * @return 返回路径，返回null则拒绝写入操作
	 */
	public static String getWritePathIgnoreError(String fileName) {
		try
		{
			MultiCardFilePath path = MultiCard.getInstance().getWritePath(fileName);
			return path.getFilePath();
		}
		catch (LimitSpaceUnwriteException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据文件类型检查外置及内置存储卡是否有空间可写
	 *
	 * @param fileType
	 *            文件类型
	 * @param type
	 *            检查范围,0表示所有,1表示只检查外置
	 * @return
	 */
	public static boolean isLimitSDCardSpaceForWrite(int fileType, int type) {
		return MultiCard.getInstance().checkSDCardSpace(fileType, type);
	}

	/**
	 * 根据文件类型检查外置及内置存储卡是否超过预警空间
	 *
	 * @param fileType
	 *            文件类型
	 * @param type
	 *            检查范围,0表示所有,1表示只检查外置
	 * @return
	 */
	public static boolean isLimitSDCardSpaceForWriteWarning(int fileType, int type) {
		return MultiCard.getInstance().islimitSpaceWarning(fileType, type);
	}

	/**
	 * 判断存储卡空间(外置、内置存储卡是否有空间可写)
	 *
	 * @param context
	 * @param fileType
	 *            文件类型
	 * @param type
	 *            检查范围,0表示所有,1表示外置
	 * @param bNeedTip
	 *            是否要做出提示语
	 * @return true表示无存储卡或无空间可写,false表示ok
	 */
	public static boolean isSDCardSapceForWriteWithTip(Context context, int fileType, int type, boolean bNeedTip) {
		if (type == 0 && !Utils.isSDcardExist())
		{
			if (bNeedTip)
			{
				Utils.showToast(context, R.string.image_save_sdcard_deny);
			}
			return true;
		}
		else if (type == 1 && !Utils.isExternalSDCardExist())
		{
			if (bNeedTip)
			{
				Utils.showToast(context, R.string.image_save_sdcard_deny);
			}
			return true;
		}

		if (!Utils.isLimitSDCardSpaceForWrite(fileType, type))
		{
			if (bNeedTip)
			{
				Utils.showToast(context, R.string.sdcard_not_enough);
			}
			return true;
		}

		if (!Utils.isLimitSDCardSpaceForWriteWarning(fileType, type))
		{
			if (bNeedTip)
			{
				Utils.showToast(context, R.string.sdcard_not_enough_warning);
			}
		}

		return false;
	}

	/**
	 * 判断是否有SDCARD
	 *
	 * @return
	 */
	public static boolean isSDcardExist() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * 判断是否有外置存储卡
	 *
	 * @return
	 */
	public static boolean isExternalSDCardExist() {
		return MultiCard.getInstance().isExternalSDCardExist();
	}

	/**
	 * 根据时间格式得到时间段
	 *
	 * @return 刚刚,?分钟前,?小时前(24小时内) 昨天 时:分(24小时之前) 前天 时:分(48小时之前) 年-月-日
	 *         时:分(72小时之前)
	 */
	public static String getTimeByDuration(long time_l) {
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
			Date date = new Date(time_l);
			String date_s = formatter.format(new Date());
			String sDay = formatter.format(date);
			int index = (Integer.valueOf(sDay) - Integer.valueOf(date_s));
			if (index == 0)
			{
				formatter = new SimpleDateFormat("HH:mm");
				return formatter.format(date);
			}
			else if (index == -1)
			{
				return "昨天";
			}
			else if (index == -2)
			{
				return "前天";
			}
			else
			{
				formatter = new SimpleDateFormat("yyyy");
				String year = formatter.format(date);
				formatter = new SimpleDateFormat("MM");
				String yue = formatter.format(date);
				formatter = new SimpleDateFormat("dd");
				String ri = formatter.format(date);
				return Integer.parseInt(year) + "-" + Integer.parseInt(yue) + "-" + Integer.parseInt(ri);
			}
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * 根据时间格式得到时间段
	 *
	 * @return 刚刚,?分钟前,?小时前(24小时内) 昨天 时:分(24小时之前) 前天 时:分(48小时之前) 年-月-日
	 *         时:分(72小时之前)
	 */
	public static String getTimeByDuration2(long time_l) {
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
			Date date = new Date(time_l);
			String date_s = formatter.format(new Date());
			String sDay = formatter.format(date);
			int index = (Integer.valueOf(sDay) - Integer.valueOf(date_s));
			if (index == 0)
			{
				formatter = new SimpleDateFormat("HH:mm");
				return formatter.format(date);
			}
			else if (index == -1)
			{
				formatter = new SimpleDateFormat("HH:mm");
				return "昨天    " + formatter.format(date);
			}
			else if (index == -2)
			{
				formatter = new SimpleDateFormat("HH:mm");
				return "前天    " + formatter.format(date);
			}
			else
			{
				formatter = new SimpleDateFormat("yyyy");
				String year = formatter.format(date);
				formatter = new SimpleDateFormat("MM");
				String yue = formatter.format(date);
				formatter = new SimpleDateFormat("dd");
				String ri = formatter.format(date);
				formatter = new SimpleDateFormat("HH:mm");
				String shifen = formatter.format(date);
				Date date02 = new Date(System.currentTimeMillis());
				formatter = new SimpleDateFormat("yyyy");
				String currYear = formatter.format(date02);
				if (Integer.parseInt(year) < Integer.parseInt(currYear))
				{
					return Integer.parseInt(year) + "-" + Integer.parseInt(yue) + "-" + Integer.parseInt(ri) + " " + shifen;
				}
				else
				{
					return Integer.parseInt(yue) + "-" + Integer.parseInt(ri) + " " + shifen;
				}
			}
		}
		catch (Exception e)
		{
			return "";
		}
	}

	public static int dp2px(Context context, int dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public static float getTextWidth(float textSize, String text) {
		Paint paint = new Paint();
		paint.setTextSize(textSize);
		return paint.measureText(text);
	}

	public static final int getBitMapWidth(int res, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
		return bitmap.getWidth();
	}

	public static final int getBitMapHeight(int res, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
		return bitmap.getHeight();
	}

	public static void byte2File(byte[] buf, String filePath, String fileName) throws Exception {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		File dir = new File(filePath);
		if (!dir.exists() && dir.isDirectory())
		{
			dir.mkdirs();
		}
		file = new File(filePath + File.separator + fileName);
		fos = new FileOutputStream(file);
		bos = new BufferedOutputStream(fos);
		bos.write(buf);
		if (bos != null) bos.close();
		if (fos != null) fos.close();
	}

	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0)
		{
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}
		else
		{
			return null;
		}
	}

	public static Bitmap getRoundBitmap(Bitmap src) {
		float radius = src.getWidth() / 2f;
		Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);

		canvas.drawCircle(radius, radius, radius, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

		canvas.drawBitmap(src, 0, 0, paint);

		return bitmap;
	}

	/**
	 * 隐藏键盘
	 *
	 * @param mInputMethodManager
	 * @param mView
	 */
	public static void hideInputMethodManager(InputMethodManager mInputMethodManager, View mView) {
		if (mInputMethodManager != null && mView != null)
		{
			mInputMethodManager.hideSoftInputFromWindow(mView.getWindowToken(), 0);
		}
	}

	public static void hideInputMethodManager(Context mContext, View view) {
		final InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null && view != null)
		{
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	//显示键盘
	public static InputMethodManager showInputMethodManager(Context mContext) {
		final InputMethodManager mInputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		mInputMethodManager.toggleSoftInput(InputType.TYPE_CLASS_PHONE, InputMethodManager.HIDE_NOT_ALWAYS);
		return mInputMethodManager;
	}

	/***
	 * 判断字符串是否是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;)
		{
			if (!Character.isDigit(str.charAt(i))) { return false; }
		}
		return true;
	}

	private static long lastClickTime;

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 1000) { return true; }
		lastClickTime = time;
		return false;
	}

	public static String formetFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024)
		{
			fileSizeString = df.format((double) fileSize) + "B";
		}
		else if (fileSize < 1048576)
		{
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		}
		else if (fileSize < 1073741824)
		{
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		}
		else
		{
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	//设置首行缩进
	public static void SetText_indent(TextView View, String Value) {

		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<pre>");
		sb.append(intentSpace);
		sb.append(Value);
		sb.append("</pre>");
		sb.append("</body>");
		sb.append("</html>");
		View.setText(Html.fromHtml(sb.toString()));
	}

	/**
	 * 获取挑战随机数
	 *
	 * @return
	 */
	public static String getNonce() {
		long temp = 10000000000L;
		long rand = (long) (Math.random() * temp);
		return String.valueOf(rand);
	}

	//获取 年月日  周
	private static SimpleDateFormat week = new SimpleDateFormat("yyyy年MM月dd日     E");

	public static String getFormatTimeWeek(long time) {
		return week.format(new Date(time));
	}

	public static void setupApp(Context context, String filePath) {
		if (context != null && (!TextUtils.isEmpty(filePath)))
		{
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
			context.startActivity(intent);
		}

	}

	public static void ForceSetupApp(Context context, String filePath) {
		setupApp(context, filePath);
		((Activity) context).finish();
	}

	/**
	 * 删除单个文件
	 *
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists())
		{
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 *
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		if (!sPath.endsWith(File.separator))
		{
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
		if (!dirFile.exists() || !dirFile.isDirectory()) { return false; }
		boolean flag = true;
		//删除文件夹下的所有文件(包括子目录)  
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			//删除子文件  
			if (files[i].isFile())
			{
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) break;
			} //删除子目录  
			else
			{
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) break;
			}
		}
		if (!flag) return false;
		//删除当前目录  
		if (dirFile.delete())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null)
		{
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 手机号验证
	 *
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,6,7,8][0-9]{9}$"); // 验证手机号  
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static boolean isStringEmpty(String str) {
		if (str == null) { return true; }

		if (str.length() <= 0) { return true; }

		if (str.equalsIgnoreCase("null")) { return true; }

		return false;
	}

	/**
	 * TODO 待确认完整的有效字符！！！！！！
	 * 判断字符串是否有效
	 *
	 * @param str
	 * @return
	 */
	public static boolean isValid(String str) {
		return str.matches("[A-Za-z0-9\u4e00-\u9fa5《》？、。，；：‘“【】）（……￥！~· ,/./?<>@#/$%&!;'\":|/+=_{}]*");
	}

	public static boolean isStringValid(String str) {
		return str.matches("[A-Za-z0-9]*");
	}

	public static void Toast(Context con,String str){
		Toast.makeText(con, str, Toast.LENGTH_SHORT).show();
	}
	//动态根据屏幕的分辨率设置控件的高度
	public static void setLayout(Context context,View view,int viewHide){
		LayoutParams ViewParams;
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		width = windowManager.getDefaultDisplay().getWidth();
		heih = windowManager.getDefaultDisplay().getHeight();
		ViewParams=view.getLayoutParams();
		ViewParams.height=width*viewHide/640;
		view.setLayoutParams(ViewParams);
	}
	//动态设置width 不充满全屏的控件
	public static void setLayout(Context context,View view ,int viewWide,int viewHeigh){
		LayoutParams ViewParams;
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		width = windowManager.getDefaultDisplay().getWidth();
		heih = windowManager.getDefaultDisplay().getHeight();
		ViewParams=view.getLayoutParams();
		ViewParams.width=viewWide*width/640;
		ViewParams.height=width*viewHeigh/640;
		view.setLayoutParams(ViewParams);
	}
	//拨打电话的公共类
	public static void call(Context context,String phoneNum){
		intent = new Intent(Intent.ACTION_DIAL);
		Uri data = Uri.parse("tel:" + phoneNum);
		intent.setData(data);
		context.startActivity(intent);
	}

}
