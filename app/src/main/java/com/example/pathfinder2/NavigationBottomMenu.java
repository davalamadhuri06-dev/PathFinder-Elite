package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NavigationBottomMenu extends BottomSheetDialogFragment {

    public static NavigationBottomMenu newInstance(String name, String email) {
        NavigationBottomMenu fragment = new NavigationBottomMenu();
        Bundle args = new Bundle();
        args.putString("NAME", name);
        args.putString("EMAIL", email);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_nav_menu, container, false);

        String name = getArguments() != null ? getArguments().getString("NAME") : "Explorer";
        String email = getArguments() != null ? getArguments().getString("EMAIL") : "";

        view.findViewById(R.id.nav_home_item).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
            dismiss();
        });

        view.findViewById(R.id.nav_gap_item).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SkillGapActivity.class));
            dismiss();
        });

        view.findViewById(R.id.nav_about_item).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AboutActivity.class));
            dismiss();
        });

        view.findViewById(R.id.nav_feedback_item).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FeedbackActivity.class);
            intent.putExtra("USER_EMAIL", email);
            startActivity(intent);
            dismiss();
        });

        return view;
    }
}
