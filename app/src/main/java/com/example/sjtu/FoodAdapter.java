package com.example.sjtu;


        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.ramotion.foldingcell.FoldingCell;

        import org.jetbrains.annotations.NotNull;

        import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<String> nameDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private ImageButton addBtn;
        private ImageButton delBtn;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            final FoldingCell fc = (FoldingCell)view.findViewById(R.id.folding_cell);

            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);
                }
            });


        }

    }

    public FoodAdapter() {
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.folding_cell_food, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.getTextView().setText(nameDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 3;
    }


}