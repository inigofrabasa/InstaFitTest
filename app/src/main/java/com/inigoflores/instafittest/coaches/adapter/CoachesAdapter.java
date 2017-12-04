package com.inigoflores.instafittest.coaches.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inigoflores.instafittest.R;
import com.inigoflores.instafittest.application.InstaFitApplication;
import com.inigoflores.instafittest.coaches.repository.Coach;
import com.inigoflores.instafittest.coaches.repository.Coaches;
import com.inigoflores.instafittest.coaches.utilities.DownloadAvatar;
import com.inigoflores.instafittest.coaches.view.CoachInformationActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by inigo on 02/12/17.
 */

public class CoachesAdapter extends RecyclerView.Adapter<CoachesAdapter.CoachesViewHolder>{

    private int resource;
    private Activity activity;
    private Coaches coaches;

    public CoachesAdapter(int resource, Activity activity, Object object){
        this.resource = resource;
        this.activity = activity;
        this.coaches = (Coaches)object;

        downloadAllImages();
    }

    private void downloadAllImages(){
        for(Coach coach : coaches.getCoaches()){
            if(coach.getAvatarLocal() == null){
            new DownloadAvatar(null, coach)
                    .execute(coach.getAvatarUrl());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public CoachesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new CoachesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoachesViewHolder holder, int position) {
        final int pos = getItemViewType(position);

        holder.nameCoach.setText(coaches.getCoaches().get(pos).getName());

        if (coaches.getCoaches().get(pos).getAvatarLocal() == null) {
                new DownloadAvatar(holder.avatarCoach, coaches.getCoaches().get(pos))
                                .execute(coaches.getCoaches().get(pos).getAvatarUrl());
        } else {
            holder.avatarCoach.setImageBitmap(
                    InstaFitApplication.bitmapInternal.loadImageFromStorage(
                            coaches.getCoaches().get(pos).getAvatarLocal(), coaches.getCoaches().get(pos).getName()));
        }

        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CoachInformationActivity.class);
                intent.putExtra(InstaFitApplication.COACH_ID_INFORMATION, coaches.getCoaches().get(pos).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coaches.getCoaches().size();
    }

    public class CoachesViewHolder extends RecyclerView.ViewHolder{

        private View listItem;
        private CircleImageView avatarCoach;
        private TextView nameCoach;

        public CoachesViewHolder(View itemView) {
            super(itemView);

            listItem = itemView.findViewById(R.id.ll_list_item);

            avatarCoach    = itemView.findViewById(R.id.ci_coach_image);
            nameCoach      = itemView.findViewById(R.id.tv_coach_name);
        }
    }
}
