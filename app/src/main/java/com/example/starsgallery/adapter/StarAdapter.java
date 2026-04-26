package com.example.starsgallery.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import de.hdodenhof.circleimageview.CircleImageView;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;
import java.util.ArrayList;
import java.util.List;

/**Adapter avec filtrage dynamique et popup de notation
 * @author DOSSAH Landry
 * @version 1.0
 */
public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private static final String TAG = "StarAdapter";

    private List<Star> celebrityList;
    private List<Star> filteredList;
    private Context appContext;
    private CelebrityFilter celebrityFilter;

    public StarAdapter(Context appContext, List<Star> celebrityList) {
        this.appContext = appContext;
        this.celebrityList = celebrityList;
        this.filteredList = new ArrayList<>(celebrityList);
        this.celebrityFilter = new CelebrityFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(appContext).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(itemView);

        // Clic sur un item : ouverture du popup de notation
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popupView = LayoutInflater.from(appContext)
                        .inflate(R.layout.star_edit_item, null, false);

                final CircleImageView popupImg = popupView.findViewById(R.id.imgPopup);
                final RatingBar popupRating = popupView.findViewById(R.id.ratingBarPopup);
                final TextView popupId = popupView.findViewById(R.id.tvPopupId);

                // Récupération de l'id de la star cliquée
                popupId.setText(((TextView) v.findViewById(R.id.tvId)).getText().toString());
                int selectedId = Integer.parseInt(popupId.getText().toString());

                // Récupération de la star depuis le service
                Star targetStar = StarService.getInstance().findById(selectedId);

                // Chargement de l'image depuis l'URL dans le CircleImageView
                Glide.with(appContext)
                        .load(targetStar.getImg())
                        .apply(RequestOptions.centerCropTransform())
                        .into(popupImg);

                popupRating.setRating(targetStar.getRating());

                AlertDialog ratingDialog = new AlertDialog.Builder(appContext)
                        .setTitle("Évaluer cette star")
                        .setMessage("Attribuez une note entre 1 et 5 :")
                        .setView(popupView)
                        .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float newScore = popupRating.getRating();
                                targetStar.setRating(newScore);
                                StarService.getInstance().update(targetStar);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();

                ratingDialog.show();
            }
        });

        return holder; // ← manquait !
    } // ← manquait !

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star current = filteredList.get(position);

        // Chargement de l'image avec Glide
        Glide.with(appContext)
                .load(current.getImg())
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.star)
                .into(holder.imgCelebrity);

        holder.tvName.setText(current.getName().toUpperCase());
        holder.ratingScore.setRating(current.getRating());
        holder.tvId.setText(String.valueOf(current.getId()));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return celebrityFilter;
    }

    // ViewHolder référençant les vues de chaque item
    public class StarViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName;
        CircleImageView imgCelebrity;
        RatingBar ratingScore;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            imgCelebrity = itemView.findViewById(R.id.imgCelebrity);
            tvName = itemView.findViewById(R.id.animeCelebrity);
            ratingScore = itemView.findViewById(R.id.ratingScore);
        }
    }

    // Classe de filtrage dynamique par nom
    public class CelebrityFilter extends Filter {

        private RecyclerView.Adapter adapter;

        public CelebrityFilter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence input) {
            List<Star> matchingStars = new ArrayList<>();

            if (input == null || input.length() == 0) {
                matchingStars.addAll(celebrityList);
            } else {
                String keyword = input.toString().toLowerCase().trim();
                for (Star entry : celebrityList) {
                    if (entry.getName().toLowerCase().startsWith(keyword)) {
                        matchingStars.add(entry);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = matchingStars;
            results.count = matchingStars.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence input, FilterResults results) {
            // Mise à jour de la liste affichée
            filteredList = (List<Star>) results.values;
            adapter.notifyDataSetChanged();
        }
    }
}