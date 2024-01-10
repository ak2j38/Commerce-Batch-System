package com.ader.commercebatchsystem.domain.entity.order

import com.ader.commercebatchsystem.domain.entity.Product
import com.ader.commercebatchsystem.domain.entity.Seller
import com.ader.commercebatchsystem.domain.enums.TaxType
import com.ader.commercebatchsystem.domain.enums.TaxTypeConverter
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
class OrderItemSnapshot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_snapshot_no")
    val id: Long? = null,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    @Column(name = "product_no")
    val productNo: Long,
    @Column(name = "seller_no")
    val sellerNo: Long,
    val sellPrice: BigDecimal? = BigDecimal.ZERO,
    val supplyPrice: BigDecimal? = BigDecimal.ZERO,
    val promotionAmount: BigDecimal? = BigDecimal.ZERO,
    val defaultDeliveryAmount: BigDecimal? = BigDecimal.valueOf(3000),
    val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,
    val itemCategory: Int? = 0,
    val taxRate: Int? = 3,
    @Convert(converter = TaxTypeConverter::class)
    val taxType: TaxType? = TaxType.TAX,
    @ManyToOne
    @JoinColumn(name = "seller_no", referencedColumnName = "id" , insertable = false, updatable = false)
    val seller: Seller,
    @ManyToOne
    @JoinColumn(name = "product_no", referencedColumnName = "id" , insertable = false, updatable = false)
    val product: Product
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItemSnapshot

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false
        if (productNo != other.productNo) return false
        if (sellerNo != other.sellerNo) return false
        if (sellPrice != other.sellPrice) return false
        if (supplyPrice != other.supplyPrice) return false
        if (promotionAmount != other.promotionAmount) return false
        if (defaultDeliveryAmount != other.defaultDeliveryAmount) return false
        if (mileageUsageAmount != other.mileageUsageAmount) return false
        if (itemCategory != other.itemCategory) return false
        if (taxRate != other.taxRate) return false
        if (taxType != other.taxType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        result = 31 * result + productNo.hashCode()
        result = 31 * result + sellerNo.hashCode()
        result = 31 * result + (sellPrice?.hashCode() ?: 0)
        result = 31 * result + (supplyPrice?.hashCode() ?: 0)
        result = 31 * result + (promotionAmount?.hashCode() ?: 0)
        result = 31 * result + (defaultDeliveryAmount?.hashCode() ?: 0)
        result = 31 * result + (mileageUsageAmount?.hashCode() ?: 0)
        result = 31 * result + (itemCategory ?: 0)
        result = 31 * result + (taxRate ?: 0)
        result = 31 * result + (taxType?.hashCode() ?: 0)
        return result
    }
}
