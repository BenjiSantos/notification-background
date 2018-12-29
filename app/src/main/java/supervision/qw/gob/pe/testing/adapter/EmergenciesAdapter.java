package supervision.qw.gob.pe.testing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import supervision.qw.gob.pe.testing.R;
import supervision.qw.gob.pe.testing.api.model.Emergencie;

public class EmergenciesAdapter extends
        RecyclerView.Adapter<EmergenciesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView numberId;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            numberId = (TextView) itemView.findViewById(R.id.numberId);
        }
    }

    private List<Emergencie> mContacts;

    // Pass in the contact array into the constructor
    public EmergenciesAdapter(List<Emergencie> contacts) {
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
        Emergencie contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.numberId;
        textView.setText(contact.getNumeroParte());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
