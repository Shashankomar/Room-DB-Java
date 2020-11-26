package com.roomdemojava.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.roomdemojava.R;
import com.roomdemojava.database.entity.CountryListEntity;
import com.roomdemojava.databinding.ItemCountryListBinding;

import java.text.MessageFormat;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {
    private Context mContext;
    private List<CountryListEntity> mCountryList;

    public CountryListAdapter(Context context, List<CountryListEntity> countryList) {
        mContext = context;
        mCountryList = countryList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryListBinding view = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_country_list, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryListEntity countryInfo = mCountryList.get(position);
        GlideToVectorYou
                .init()
                .with(mContext)
                .setPlaceHolder(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground)
                .load(Uri.parse(countryInfo.getFlag()), holder.mBinding.ivImageFlag);

        holder.mBinding.tvName.setText(countryInfo.getCountryName());
        holder.mBinding.tvCapital.setText(countryInfo.getCapital());
        holder.mBinding.tvRegion.setText(countryInfo.getRegion());
        holder.mBinding.tvSubRegion.setText(countryInfo.getSubRegion());
        holder.mBinding.tvLanguage.setText(countryInfo.getLanguage());
        holder.mBinding.tvPopulation.setText(MessageFormat
                .format("{0}", countryInfo.getPopulation()));
        holder.mBinding.tvBorders.setText(countryInfo.getBorders());
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        private final ItemCountryListBinding mBinding;

        public CountryViewHolder(ItemCountryListBinding bind) {
            super(bind.getRoot());
            mBinding = bind;
        }
    }
}