package com.ecs.wonderful200.sqllitenotes026.adapter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ecs.wonderful200.sqllitenotes026.bean.SQLBean;
import com.ecs.wonderful200.sqllitenotes026.R;
import com.ecs.wonderful200.sqllitenotes026.R.drawable;
import com.ecs.wonderful200.sqllitenotes026.R.id;
import com.ecs.wonderful200.sqllitenotes026.R.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class MainAdapter extends BaseAdapter {
	private List<SQLBean> list;
	private String type;
	private int setsum;
	private Context context;
	private LayoutInflater inflater;
	private DisplayMetrics dm;

	public MainAdapter(List<SQLBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.note_item, null);
			holder.tv_note_id = (TextView) convertView
					.findViewById(R.id.tv_note_id);
			holder.tv_locktype= (TextView) convertView
					.findViewById(R.id.tv_locktype);
			holder.tv_lock= (TextView) convertView
					.findViewById(R.id.tv_lock);
			holder.tv_note_title = (TextView) convertView
					.findViewById(R.id.tv_note_title);
			holder.tv_note_time = (TextView) convertView
					.findViewById(R.id.tv_note_time);
			holder.iv_imge = (ImageView) convertView.findViewById(R.id.iv_imge);
			holder.im_datatime = (ImageView) convertView
					.findViewById(R.id.im_datatime);
			holder.ll_bg = (RelativeLayout) convertView
					.findViewById(R.id.ll_bg);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		dm = context.getResources().getDisplayMetrics();
		LinearLayout.LayoutParams imagebtn_params = new LinearLayout.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		imagebtn_params.width = dm.widthPixels / 2;
		imagebtn_params.height = dm.widthPixels / 2;
		holder.ll_bg.setLayoutParams(imagebtn_params);
		holder.tv_note_id.setText(list.get(position).get_id());
		holder.tv_note_title.setText(list.get(position).getTitle());
		holder.tv_note_time.setText(list.get(position).getTime());
		holder.tv_locktype.setText(list.get(position).getLocktype());
		holder.tv_lock.setText(list.get(position).getLock());

		if (list.get(position).getDatatype().equals("0")) {
			holder.im_datatime.setVisibility(View.GONE);
		} else {
			holder.im_datatime.setVisibility(View.VISIBLE);
		}
		if ("0".equals(list.get(position).getLocktype())) {
			String context = list.get(position).getContext();
			// 定义正则表达式，用于匹配路径
			Pattern p = Pattern.compile("/([^\\.]*)\\.\\w{3}");
			Matcher m = p.matcher(context);
			while (m.find()) {
				// 取出路径
				String path = m.group().toString();
				// 取出路径的后缀
				String type = path.substring(path.length() - 3, path.length());
				Bitmap bm = null;
				Bitmap rbm = null;
				// 判断附件的类型，如果是录音文件，则从资源文件中加载图片
				if (type.equals("amr")) {
				} else {
					// 取出图片
					if (holder.iv_imge.getDrawable() == null) {
						FileInputStream f;
						try {
							f = new FileInputStream(m.group());
							BitmapFactory.Options options = new BitmapFactory.Options();
							options.inJustDecodeBounds = true;
							Bitmap bmp = BitmapFactory.decodeFile(m.group(),
									options);
							options.inSampleSize = options.outHeight / 100; /* 图片长宽方向缩小倍数 */
							options.inJustDecodeBounds = false;
							options.inDither = false; /* 不进行图片抖动处理 */
							options.inPreferredConfig = null; /* 设置让解码器以最佳方式解码 */
							/* 下面两个字段需要组合使用 */
							options.inPurgeable = true;
							options.inInputShareable = true;
							bm = BitmapFactory.decodeFile(m.group(), options);

						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						holder.iv_imge.setVisibility(View.VISIBLE);
						holder.tv_note_title.setLines(1);
						holder.iv_imge.setImageBitmap(bm);

					}
				}
			}
		} else {
			holder.tv_note_title.setVisibility(View.GONE);
			holder.iv_imge.setVisibility(View.VISIBLE);
			holder.iv_imge.setBackgroundResource(R.drawable.lock_bg);
		}
		return convertView;
	}

	class Holder {
		public TextView tv_note_id, tv_note_title, tv_note_time,tv_locktype,tv_lock;
		public RelativeLayout ll_bg;
		public ImageView iv_imge, im_datatime;
	}
}
