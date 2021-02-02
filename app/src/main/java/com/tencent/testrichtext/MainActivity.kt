package com.tencent.testrichtext

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.*
import android.text.Html.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL
import android.text.style.ClickableSpan
import android.text.style.ReplacementSpan
import android.text.style.URLSpan
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.getSpans
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tencent.testrichtext.multiadapter.IMultiAdapter
import com.tencent.testrichtext.multiadapter.IMultiple
import com.tencent.testrichtext.multiadapter.MultiAdapter


class MainActivity : AppCompatActivity() {
    private val textBlock = TextBlock()
    private val imgBlock = ImgBlock()
    private val viewModel = DataVieModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MultiAdapter()
        val rv: RecyclerView = findViewById(R.id.rvContent)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        adapter.addItemType(TextBlock.TextBean::class.java, textBlock, 0)
        adapter.addItemType(ImgBlock.ImgBean::class.java, imgBlock, 1)

        viewModel.getData().observe(this) {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }


    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        menu.findItem(0).setOnMenuItemClickListener {
            viewModel.getData(true)
            false
        }
        return super.onMenuOpened(featureId, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0, "编辑")
        return super.onCreateOptionsMenu(menu)
    }


}

class ImgBlock : IMultiAdapter<ImgBlock.ImgHolder, ImgBlock.ImgBean> {
    class ImgHolder(view: ImageView) : RecyclerView.ViewHolder(view)
    data class ImgBean(
            var id: String,
            var url: Int,
    ) : IMultiple

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgHolder {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val editText = ImageView(parent.context)
        editText.layoutParams = layoutParams
        editText.scaleType = ImageView.ScaleType.CENTER_INSIDE
        return ImgHolder(editText)
    }

    override fun onBindViewHolder(holder: ImgHolder, data: ImgBean) {
        (holder.itemView as? ImageView)?.let {
            Glide.with(holder.itemView).load(data.url).into(it)
        }
    }
}

class UrlClickableSpan(
        val url: String,//网址
) : ClickableSpan() {
    override fun onClick(widget: View) {
        Log.e("siyehua", url)
//        val intent = Intent(widget.context, CommonH5Activity::class.java)
//        intent.putExtra("url", url)
//        widget.context.startActivity(intent)
    }

    override fun updateDrawState(ds: TextPaint) {
        //设置颜色
        ds.color = Color.parseColor("#FF9800")
        //设置是否要下划线
        ds.isUnderlineText = false
    }
}

class FrameSpan : ReplacementSpan() {
    private val mPaint: Paint
    private var mWidth = 0
    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        //return text with relative to the Paint
        mWidth = paint.measureText(text, start, end).toInt()
        return mWidth
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        text ?: return
        //draw the frame with custom Paint
//        canvas.drawRect(x, top.toFloat(), x + mWidth, bottom.toFloat(), mPaint)
        canvas.drawText(text, start, end, x, y.toFloat(), paint)
    }

    init {
        mPaint = Paint()
        mPaint.setStyle(Paint.Style.STROKE)
        mPaint.setColor(Color.BLUE)
        mPaint.setAntiAlias(true)
    }
}

class TextBlock : IMultiAdapter<TextBlock.TextHolder, TextBlock.TextBean> {
    class TextHolder(view: EditText) : RecyclerView.ViewHolder(view)
    data class TextBean(
            var id: String,
            var text: String,
            var edit: Boolean = false
    ) : IMultiple

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextHolder {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val editText = EditText(parent.context)
        editText.isCursorVisible = false
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.layoutParams = layoutParams
        editText.minHeight = 20
        editText.background = null
        editText.setTextColor(Color.BLACK)
        editText.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                editText.setCursorVisible(false)
                editText.setCursorVisible(true)
            }

            override fun onViewDetachedFromWindow(v: View) {}
        })
        editText.doAfterTextChanged { edit ->
            var result: String = edit?.toString() ?: ""
            if (edit is SpannableStringBuilder) {
                result=   Html.toHtml(edit,TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
//                val urlSpans = edit.getSpans<UrlClickableSpan>()
//                urlSpans.forEach { span ->
//                    val start = edit.getSpanStart(span)
//                    val end = edit.getSpanEnd(span)
//                    if (end > start) {
//                        val text = result.subSequence(start, end)
//                        result = result.replaceRange(start, end, "<a href=${span.url}>$text</a>")
//                    }
//                }
            }
            Log.e("siyehua", "result: $result")

        }
        return TextHolder(editText)
    }

    /**
     * html  带有<font>标签的文本
     */
    private fun getClickableHtml(html: String?): CharSequence {
        val spannedHtml: Spanned = Html.fromHtml(html)
        val clickableHtmlBuilder = SpannableStringBuilder(spannedHtml)
        val spans = clickableHtmlBuilder.getSpans(0, spannedHtml.length, URLSpan::class.java)
        for (value in spans) {
            setLinkClickable(clickableHtmlBuilder, value)
        }
        return clickableHtmlBuilder
    }


    /**
     * 捕获<a>标签点击事件
     */
    private fun setLinkClickable(clickableHtmlBuilder: SpannableStringBuilder, urlSpan: URLSpan) {
        val start = clickableHtmlBuilder.getSpanStart(urlSpan)
        val end = clickableHtmlBuilder.getSpanEnd(urlSpan)
        val flags = clickableHtmlBuilder.getSpanFlags(urlSpan)
        val clickableSpan = UrlClickableSpan(urlSpan.url)
        clickableHtmlBuilder.setSpan(clickableSpan, start, end, flags)
        clickableHtmlBuilder.removeSpan(urlSpan)
    }


    override fun onBindViewHolder(holder: TextHolder, data: TextBean) {
        (holder.itemView as? EditText)?.apply {
            setText(getClickableHtml(data.text))
            isCursorVisible = data.edit
            isFocusable = data.edit
            isFocusableInTouchMode = data.edit
            movementMethod = LinkMovementClickMethod.getInstance()
        }
    }
}