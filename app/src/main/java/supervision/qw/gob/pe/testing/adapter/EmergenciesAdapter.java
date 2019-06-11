package supervision.qw.gob.pe.testing.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import supervision.qw.gob.pe.testing.Bottom_navigation;
import supervision.qw.gob.pe.testing.R;
import supervision.qw.gob.pe.testing.api.model.Emergencie;
import supervision.qw.gob.pe.testing.api.model.EmergencieObject;

public class EmergenciesAdapter extends
        RecyclerView.Adapter<EmergenciesAdapter.ViewHolder> {
    private Context context;

    public EmergenciesAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private String mItem;
        public TextView typeEmergencieId;
        public TextView dateEmergencie;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            typeEmergencieId = (TextView) itemView.findViewById(R.id.typeEmergencieId);
            dateEmergencie = (TextView) itemView.findViewById(R.id.dateEmergencie);
        }

    }

    private List<EmergencieObject> mContacts;

    // Pass in the contact array into the constructor
    public EmergenciesAdapter(List<EmergencieObject> contacts) {
        mContacts = contacts;
    }

    @Override
    public EmergenciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_emergencies, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(EmergenciesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final EmergencieObject contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView typeEmergencieView = viewHolder.typeEmergencieId;
        typeEmergencieView.setText(contact.getTipoEmergencia());

        TextView dateEmergencieView = viewHolder.dateEmergencie;
        dateEmergencieView.setText(contact.getFechaParte());



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String url = "waze://?ll="+contact.getLongitud()+","+contact.getLatitud()+"&navigate=yes";
                    Log.d("URL", "URL: " +url + " ");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
                catch (ActivityNotFoundException ex)
                {
                    // If Waze is not installed, open it in Google Play:
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                    v.getContext().startActivity(intent);
                }
                Log.d("CLICKEADO", "onClick " + contact.getNumeroParte() + " ");
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
