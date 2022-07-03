package com.example.training0703_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //レイアウトファイルからViewオブジェクトを生成
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        activity?.let{
            view.findViewById<ListView>(R.id.list).apply {
                //ビュー上のListViewを準備済みのデータで初期化
                adapter = ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    ListDataSource.getAllNames()
                )
                //リスト項目をクリックした時に詳細情報を置換
                setOnItemClickListener { parent, view, pos, id ->
                    val fragment = DetailsFragment()
                    //フラグメントに値を設定
                    fragment.arguments = Bundle().apply {
                        putString("name", parent.getItemAtPosition(pos).toString())
                    }
                    //フラグメントの操作を開始
                    parentFragmentManager.beginTransaction()
                    //フラグメントの置き換え
                        .replace(R.id.detailsFrame, fragment)
                        .commit()
                }
            }
        }




        return super.onCreateView(inflater, container, savedInstanceState)
    }
}