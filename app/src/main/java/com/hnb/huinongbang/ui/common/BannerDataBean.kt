package com.hnb.huinongbang.ui.common

import com.hnb.huinongbang.logic.network.ServiceCreator

class BannerDataBean {
    var imageRes: Int? = null
    var imageUrl: String? = null
    var title: String?
    var viewType: Int

    constructor(imegeRes: Int?, title: String?, viewType: Int) {
        this.imageRes = imageRes
        this.title = title
        this.viewType = viewType
    }
    constructor(imageUrl: String?, title: String?, viewType: Int) {
        this.imageUrl = imageUrl
        this.title = title
        this.viewType = viewType
    }

    companion object {
        val shoppingData: List<BannerDataBean> get() {
            val list: MutableList<BannerDataBean> = ArrayList()
            list.add(
                BannerDataBean(ServiceCreator.lunbo+"1.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo+"2.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo+"3.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo+"4.jpg", null, 1)
            )
            return list
        }
        val donateData: List<BannerDataBean> get() {
            val list: MutableList<BannerDataBean> = ArrayList()
            list.add(
                BannerDataBean(ServiceCreator.lunbo_donate+"sqet01.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_donate+"sqet02.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_donate+"sqet03.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_donate+"sqet04.png", null, 1)
            )
            return list
        }

    }

}