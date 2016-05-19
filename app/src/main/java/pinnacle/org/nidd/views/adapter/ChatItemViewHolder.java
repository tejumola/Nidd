package pinnacle.org.nidd.views.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;
import pinnacle.org.nidd.R;
import pinnacle.org.nidd.model.Chat;
import pinnacle.org.nidd.model.Operator;
import pinnacle.org.nidd.model.Visitor;
import pttextview.widget.PTTextView;

/**
 * Created by DOTECH on 28/04/2016.
 */
public class ChatItemViewHolder extends RecyclerView.ViewHolder{
    private final String TAG=getClass().getSimpleName();
    private CircleImageView visitorAvatar;
    private PTTextView visitorName,createdDate,visitorLastMessage,counterText;
    private Visitor visitor;
    private Context context;

    public ChatItemViewHolder(View itemView) {
        super(itemView);
        this.context=itemView.getContext();
        initViews(itemView);
    }

    /**
     * innnitilize the views and wire
     * @param itemView
     */
    private void initViews(View itemView) {
        visitorAvatar=(CircleImageView)itemView.findViewById(R.id.visitor_imageView);
        visitorName=(PTTextView)itemView.findViewById(R.id.visitor_name);
        createdDate=(PTTextView)itemView.findViewById(R.id.created_date);
        visitorLastMessage=(PTTextView)itemView.findViewById(R.id.visitor_lastMessage);
        counterText=(PTTextView)itemView.findViewById(R.id.chat_Counter);
    }

    /**
     * set the chat item
     * @param item
     */
    public void setItem(Visitor item) {
        this.visitor = item;
        setContent();
    }

    private void setContent() {

        final String visitorName=visitor.asOperator().getUserName();
        this.visitorName.setText(visitorName);

       // final String createdDate=this.chat.getDate().toString();
        //this.createdDate.setText(createdDate);

        //final String visitorLastMessage=this.chat.getLastMessage();
        //this.visitorLastMessage.setText(visitorLastMessage);

        byte[] imageBytes=visitor.asOperator().getUserAvatar();
        if(visitor.asOperator().getUserAvatar()==null){
            visitorAvatar.setImageResource ( R.drawable.avatar_moss );
            return;
        }
        //otherwise set the user avatar image
        Bitmap bitmap= BitmapFactory.decodeByteArray ( imageBytes,0,imageBytes.length);
        visitorAvatar.setImageBitmap(bitmap);

    }
}
