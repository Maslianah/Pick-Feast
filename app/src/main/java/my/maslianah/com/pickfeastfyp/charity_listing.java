package my.maslianah.com.pickfeastfyp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import my.maslianah.com.pickfeastfyp.model.rowPack;

public class charity_listing  extends ArrayAdapter<rowPack> {

    Context context;

    //public final static String CUSTOM_ADAPTER_PACKAGE_DETAILS = "my.maslianah.com.pickfeastfyp.CUSTOM_ADAPTER_PACKAGE_DETAILS";

    public charity_listing(Context context, int resourceId,
                         List<rowPack> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
       // Button btnViews;
        TextView txtDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final rowPack rows = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.charity_listing, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.textViewListing);
            //holder.btnViews = (Button) convertView.findViewById(R.id.buttonView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.displayPic);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtDesc.setText(rows.getDesc());
        holder.imageView.setImageResource(rows.getImageId());
       /* holder.btnViews.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                // TODO Auto-generated method stub

            }
        });*/
        return convertView;
    }
}



/*extends BaseAdapter {

    Context c;
    ArrayList<Caterer> aCaterer;

    public CustomAdapter(Context c, ArrayList<Caterer> aCaterer) {
        this.c = c;
        this.aCaterer = aCaterer;
    }

    @Override
    public int getCount() {
        return aCaterer.size();
    }

    @Override
    public Object getItem(int position) {
        return aCaterer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     *//*   if(convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.name);
        TextView namText = (TextView) convertView.findViewById(R.id.name);
        TextView naeText = (TextView) convertView.findViewById(R.id.name);

        final Customer cust = (Customer) this.getItem(position);
        nameText.setText(cust.getCustID());
        namText.setText(cust.getCustID());
        naeText.setText(cust.getCustID());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,cust.getCustName(),Toast.LENGTH_SHORT).show();
            }
        });*//*
        return convertView;
    }
}
*/