package com.ugnext.training.expenditureapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    ArrayList<TransactionsClass> transactions = new ArrayList<>();

    public void setTransactions(ArrayList<TransactionsClass> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.transactionDate.setText(transactions.get(position).getTransactionDate());
        holder.reason.setText(transactions.get(position).getReason());
        holder.amount.setText(transactions.get(position).getAmount());
        holder.transactionDate.setText(transactions.get(position).getTransactionDate());
        holder.type.setText(transactions.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView reason, transactionDate, amount, type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reason = itemView.findViewById(R.id.reason);
            transactionDate = itemView.findViewById(R.id.transactionDate);
            amount = itemView.findViewById(R.id.amount);
            type = itemView.findViewById(R.id.type);

        }
    }
}
