package com.tencent.testrichtext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tencent.testrichtext.multiadapter.IMultiple

class DataVieModel : ViewModel() {
    private val live: MutableLiveData<List<IMultiple>> = MutableLiveData()


    fun getData(edit: Boolean = false): LiveData<List<IMultiple>> {
        val data = mutableListOf<IMultiple>()
        data.add(TextBlock.TextBean("123", """<h1><a href="http://www.baidu.com">小女生的海岛梦</a><h1>""", edit))
        data.add(
            TextBlock.TextBean(
                "123",
                """不知道姑娘们怎么想，我觉得在碧海蓝天下穿比bikini拍照这个海岛梦很小的时候就开始萌芽了。

初高中一放学路过报摊就会挪不动腿，翻几页昕薇、伊周，看到上面身材面容姣好的女明星夏日泳衣装扮美得不行，总是想象自己有一天去国外度假会是什么样子。

当年坐在大学宿舍里吹空调捧着大西瓜痴痴盯着美女博主沙滩照的那个我，转眼也已经在 美国 经历了硕士留学、工作三年忙忙碌碌的生活。

来美这么些年，我的身材跟大学相比并没有很明显的变化。直到年初有一天起床的时候照镜子，掐了一下后背松松垮垮的肉，突然有种感叹：自己的身材为什么跟大妈一样虎背熊腰！！！

正是对自己 “由衷” 的嫌弃，才狠下心来把健身、跑步、练腹肌提上2017计划表。

经过这半年坚持锻炼，调整饮食习惯，尽量自己在家做饭，带便当，我从一个完全没有肌肉，一个松垮型的“瘦胖子” 逐渐锻炼成自己想要的样子：二头肌、脊柱沟开始成形，腿臀肌肉也变得比以前结实，锁骨明显。

虽然达不到我的女神大表姐刘雯那样的完美身材，但我已经准备好离开 波士顿 ，这个夏天拥抱碧海蓝天的 波多黎各 了！

多年来海岛梦的Dream destination 其实是 夏威夷 ，但 波多黎各 离 波士顿 近，开销也没有那么大，海岛景色也是一样的美，斟酌一番后就选了这个 美国 自由邦作为旅行目的地啦～

（介绍一下寄己：一个爱健康生活方式、爱健身、爱旅行的 广州 姑娘，美研毕业工作一年，现居 北京 ，更多关于我最新的旅行和生活方式日常可在 Instagram@stefaniyeung 找到我～）""",
                edit
            )
        )
        data.add(TextBlock.TextBean("123", """第一天： 初见加勒比蓝""", edit))
        data.add(
            TextBlock.TextBean(
                "123",
                """我跟男朋友鹏鹏是先从 波士顿 飞 迈阿密 ，中转停留1个小时后下午3点多到达 波多黎各 首府 圣胡安 。一下机场已经感受到扑面而来的一股热浪，很是兴奋～

我们搭乘机场shuttle 去 租车 公司 取车 后，10来分钟就开到了La Concha度假酒店。酒店非常大，就在 圣胡安 市区，特别特别宽敞，跟大部分resort一样，沙滩酒吧餐厅泳池健身房所有设施都一应俱全，我们进去不久就有服务生拿饮料过来，还挺贴心。

放下行李直奔酒店的private beach, 第一次看到 加勒比海 的蓝色，跟我所在的美东新 英格兰 夏天很不一样，看着眼前海天一线的壮阔美景，就这样开始了期待已久的 波多黎各 之旅。""",
                edit
            )
        )
        data.add(TextBlock.TextBean("123", """第一天： 初见加勒比蓝""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a001))
        data.add(TextBlock.TextBean("123", """碧海蓝天椰子树，随手用手机拍都是景致""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a002))
        data.add(TextBlock.TextBean("123", """手机抓拍到一对情侣随意漫步在La Concha 酒店的沙滩上～""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a003))
        data.add(TextBlock.TextBean("123", """在酒店阳台向下俯瞰， 加勒比海 蓝得让人惊叹""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a004))
        data.add(TextBlock.TextBean("123", """酒店露台边上随手拍～ 已经嗅到了海水的味道！""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a005))
        data.add(TextBlock.TextBean("123", """超级舒服的无边泳池！面朝大海，虽然眼前没有春暖花开，能让我泡在泳池就很开心了哈哈～""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a006))
        data.add(
            TextBlock.TextBean(
                "123",
                """非常喜欢这套Boys and Arrows 竖条纹泳衣，买的时候心已经飞到海岛了！朋友看到我发票圈都说原来你健身是为了穿bikini拍照😄 （瞬间被发现小心机了）

临近日落的时候我跟鹏鹏决定开车去Old City of San Juan老城区逛逛～

老城内各种糖果色低矮的房子很有味道，游客也不多，很适合随意散步。""",
                edit
            )
        )
        data.add(ImgBlock.ImgBean("123", R.drawable.a007))
        data.add(ImgBlock.ImgBean("123", R.drawable.a008))
        data.add(ImgBlock.ImgBean("123", R.drawable.a009))
        data.add(
            TextBlock.TextBean(
                "123", """虽说7月-12月是雨季，不是大家公认的旅游旺季，但我觉得温度刚刚合适，没有想象中热， 波士顿 夏天高温的时候也像 圣胡安 现在29，30度。

我带的基本上就是夏装，比较喜欢裹胸、露背的裙子，配上一顶宽檐草帽，适合海岛风格～""", edit
            )
        )
        data.add(ImgBlock.ImgBean("123", R.drawable.a010))
        data.add(ImgBlock.ImgBean("123", R.drawable.a011))
        data.add(TextBlock.TextBean("123", """老城一角""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a012))
        data.add(
            TextBlock.TextBean(
                "123", """温柔的粉色墙～

老城不论什么时候都特别多人，晚上9点拥堵都是常态，因为地方小，而且都是弯弯曲曲的小路，如果驾车的话建议把车停在Le Perla沿海边。""", edit
            )
        )
        data.add(ImgBlock.ImgBean("123", R.drawable.a013))
        data.add(
            TextBlock.TextBean(
                "123",
                """我们晚上去的一家吃monfogo 好评比较高的餐厅，叫cafe puerto rico，我特别喜欢看yelp 搜美食，而且评论会看十几个，这家是看了好半天决定来的。""",
                edit
            )
        )
        data.add(ImgBlock.ImgBean("123", R.drawable.a014))
        data.add(
            TextBlock.TextBean(
                "123", """Mofongo 是当地的特色菜，感觉像是土豆饼里夹杂了各种海鲜，海鲜烩饭也很不错！
地址是Calle O’Donnell, 208, San Juan, Puerto Rico 00901""", edit
            )
        )
        data.add(ImgBlock.ImgBean("123", R.drawable.a015))
        data.add(
            TextBlock.TextBean(
                "123",
                """饭后走到国家历史遗址附近散步看日落，景色也是非常美。这里是当年 西班牙 士兵驻守的岗哨，沿路会看到很多类似的亭子。""",
                edit
            )
        )
        data.add(ImgBlock.ImgBean("123", R.drawable.a016))
        data.add(TextBlock.TextBean("123", """粉红色天空下的 圣胡安 老城～""", edit))
        data.add(ImgBlock.ImgBean("123", R.drawable.a017))
        data.add(
            TextBlock.TextBean(
                "123",
                """第一天在 波多黎各 的感受就是：老城也太适合拍照了吧！古朴而有质感的石板路、色彩斑斓、百年历史的 西班牙 式建筑、转角就碰到开得正盛的热带植物，感觉每走几步就能发现不同的惊喜。也让我们对接下来的行程充满向往！""",
                edit
            )
        )
        live.value = data
        return live
    }
}