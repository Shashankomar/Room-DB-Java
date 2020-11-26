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
import com.roomdemojava.databinding.ItemCountryListBinding;
import com.roomdemojava.model.CountryListModel;

import java.text.MessageFormat;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private Context mContext;
    private List<CountryListModel> mCountryList;

    public CountryListAdapter(Context context, List<CountryListModel> countryList) {
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
        CountryListModel countryInfo = mCountryList.get(position);
        int index = 0;

        GlideToVectorYou
                .init()
                .with(mContext)
                .setPlaceHolder(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground)
                .load(Uri.parse(countryInfo.flag), holder.mbinding.ivImageFlag);

        holder.mbinding.tvName.setText(countryInfo.name);
        holder.mbinding.tvCapital.setText(countryInfo.capital);
        holder.mbinding.tvRegion.setText(countryInfo.region);
        holder.mbinding.tvSubRegion.setText(countryInfo.subregion);
        holder.mbinding.tvLanguage.setText(countryInfo.languages.get(index).name);
        holder.mbinding.tvPopulation.setText(MessageFormat.format("{0}", countryInfo.population));

        for (int i = 0; i < countryInfo.borders.size(); i++) {
            holder.mbinding.tvBorders.setText(countryInfo.borders.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        private final ItemCountryListBinding mbinding;

        public CountryViewHolder(ItemCountryListBinding bind) {
            super(bind.getRoot());
            mbinding = bind;
        }
    }
}