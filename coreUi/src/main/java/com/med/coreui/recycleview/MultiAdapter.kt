package com.med.coreui.recycleview

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

interface ItemDelegate<VH> : DiffItem, ViewType {
	fun onCreateViewHolder(parent: ViewGroup): VH
	fun onBindViewHolder(holder: VH)
	fun onViewRecycled(holder: VH) = Unit

	fun onViewAttachedToWindow(holder: VH) = Unit
	fun onViewDetachedFromWindow(holder: VH) = Unit
}

abstract class ItemDelegateAdapter :
	ListAdapter<ItemDelegate<RecyclerView.ViewHolder>, RecyclerView.ViewHolder>(
		AsyncDifferConfig.Builder(DiffUtilCallback<ItemDelegate<RecyclerView.ViewHolder>>()).build()
	) {
	private var items: List<ItemDelegate<RecyclerView.ViewHolder>>? = null
	private var viewPool: RecyclerView.RecycledViewPool? = null

	protected abstract fun onCreateFallback(
		parent: ViewGroup,
		viewType: Int
	): RecyclerView.ViewHolder

	@Suppress("UNCHECKED_CAST")
	fun <T : RecyclerView.ViewHolder> submitListT(list: List<ItemDelegate<T>>?) {
		submitList(list as? List<ItemDelegate<RecyclerView.ViewHolder>>?)
	}

	override fun submitList(list: List<ItemDelegate<RecyclerView.ViewHolder>>?) {
		items = list
		super.submitList(list)
	}

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		viewPool = recyclerView.recycledViewPool
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		viewPool = null
	}

	override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
		getItemByHolder(holder)?.onViewAttachedToWindow(holder)
	}

	override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
		holder.itemView.clearAnimation()
		getItemByHolder(holder)?.onViewDetachedFromWindow(holder)
	}

	override fun getItemViewType(position: Int): Int {
		return getItem(position).getViewType()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return getItemByViewType(viewType)?.onCreateViewHolder(parent) ?: onCreateFallback(
			parent,
			viewType
		)
	}

	private fun getItemByViewType(viewType: Int): ItemDelegate<RecyclerView.ViewHolder>? {
		return items?.firstOrNull { it.getViewType() == viewType }
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = getItemNullable(position)
		item?.onBindViewHolder(holder)
	}

	override fun onBindViewHolder(
		holder: RecyclerView.ViewHolder,
		position: Int,
		payloads: MutableList<Any>
	) {
		if (payloads.isEmpty()) {
			//blink view (plusOrReplace whole item)
			super.onBindViewHolder(holder, position, payloads)
		} else {
			//not blink view
			onBindViewHolder(holder, position)
		}
	}

	override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
		getItemByHolder(holder)?.onViewRecycled(holder)
	}

	private fun getItemByHolder(holder: RecyclerView.ViewHolder): ItemDelegate<RecyclerView.ViewHolder>? {
		return getItemNullable(holder.adapterPosition)
	}

	private fun getItemNullable(position: Int): ItemDelegate<RecyclerView.ViewHolder>? {
		return if (position != NO_POSITION)
			try {
				items?.get(position)
			} catch (ex: Exception) {
				Timber.v("Items between Adapter and DiffUtils are not synchronized, use DiffUtils item instead")
				try {
					getItem(position)
				} catch (ex: Exception) {
					Timber.v("Cannot find item at position $position on both Adapter and DiffUtils")
					null
				}
			}
		else {
			null
		}
	}
}

class MultiAdapter<VH : RecyclerView.ViewHolder>(
	private val viewHolderFactory: (parent: ViewGroup, viewType: Int) -> VH
) : ListAdapter<DisplayItem<VH>, VH>(
	AsyncDifferConfig.Builder(DiffUtilCallback<DisplayItem<VH>>()).build()
) {
	private var items: List<DisplayItem<VH>>? = null
	private var viewPool: RecyclerView.RecycledViewPool? = null

	override fun submitList(list: List<DisplayItem<VH>>?) {
		items = list
		super.submitList(list)
	}

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		viewPool = recyclerView.recycledViewPool
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		viewPool = null
	}

	override fun onViewAttachedToWindow(holder: VH) {
		getItemByHolder(holder)?.onViewAttachedToWindow(holder)
	}

	override fun onViewDetachedFromWindow(holder: VH) {
		holder.itemView.clearAnimation()
		getItemByHolder(holder)?.onViewDetachedFromWindow(holder)
	}

	override fun getItemViewType(position: Int): Int {
		return getItem(position).getViewType()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
		val holder = viewHolderFactory(parent, viewType)
		getItemByViewType(viewType)?.afterCreateViewHolder(viewPool, holder)
		return holder
	}

	private fun getItemByViewType(viewType: Int): DisplayItem<VH>? {
		return items?.firstOrNull { it.getViewType() == viewType }
	}

	override fun onBindViewHolder(holder: VH, position: Int) {
		val item = getItemNullable(position)
		item?.onBindViewHolder(holder)
	}

	override fun onBindViewHolder(
		holder: VH,
		position: Int,
		payloads: MutableList<Any>
	) {
		if (payloads.isEmpty()) {
			//blink view (plusOrReplace whole item)
			super.onBindViewHolder(holder, position, payloads)
		} else {
			//not blink view
			onBindViewHolder(holder, position)
		}
	}

	override fun onViewRecycled(holder: VH) {
		getItemByHolder(holder)?.onViewRecycled(holder)
	}

	private fun getItemByHolder(holder: VH): DisplayItem<VH>? {
		return getItemNullable(holder.adapterPosition)
	}

	private fun getItemNullable(position: Int): DisplayItem<VH>? {
		return if (position != NO_POSITION)
			try {
				items?.get(position)
			} catch (ex: Exception) {
				Timber.v("Items between Adapter and DiffUtils are not synchronized, use DiffUtils item instead")
				try {
					getItem(position)
				} catch (ex: Exception) {
					Timber.v("Cannot find item at position $position on both Adapter and DiffUtils")
					null
				}
			}
		else {
			null
		}
	}
}


