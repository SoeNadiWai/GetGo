package com.soenadiwai.getgo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapterForCarList(var carList : List<Car>) :
    RecyclerView.Adapter<RVAdapterForCarList.CarViewHolder>() {

    private var mItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.car_item_view, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList[position], mItemClickListener)
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var carNameTxt: TextView = itemView.findViewById(R.id.carname_textView)
        var modelNoTxt: TextView = itemView.findViewById(R.id.modelNo_textView)
        var seaterTxt: TextView = itemView.findViewById(R.id.seater_textView)
        var distanceTxt: TextView = itemView.findViewById(R.id.distance_textView)
        var rentalFeeTxt: TextView = itemView.findViewById(R.id.rentalFee_textView)
        var mileageFeeTxt: TextView = itemView.findViewById(R.id.mileageFee_textView)
        var carImageView: ImageView = itemView.findViewById(R.id.car_imageView)


        fun bind(car: Car, mItemClickListener: OnItemClickListener?) {
            carNameTxt.text = car.carName
            modelNoTxt.text = car.modelNo
            seaterTxt.text = car.seater.toString() + " Seater"
            distanceTxt.text = car.distance.toString() + " AWAY"
            rentalFeeTxt.text = "Fr.$"+ car.rentalFee.toString() + "/hr"
            mileageFeeTxt.text = "$"+ car.milageFee.toString() + "/km"
            carImageView.setImageResource(car.image)

        }

    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }


}