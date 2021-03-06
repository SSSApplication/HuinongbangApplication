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

        val policyData: List<BannerDataBean> get() {
            val list: MutableList<BannerDataBean> = ArrayList()
            list.add(
                BannerDataBean(ServiceCreator.lunbo_policy+"policy1.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_policy+"policy3.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_policy+"p4.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_policy+"policy4.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_policy+"policy2.jpg", null, 1)
            )
            return list
        }

        val plantingData: List<BannerDataBean> get() {
            val list: MutableList<BannerDataBean> = ArrayList()
            list.add(
                BannerDataBean(ServiceCreator.lunbo_plant+"n4.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_plant+"n3.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_plant+"1553497932.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_plant+"n5.jpg", null, 1)
            )
            list.add(
                BannerDataBean(ServiceCreator.lunbo_plant+"nongye.jpg", null, 1)
            )
            return list
        }

    }

    }

