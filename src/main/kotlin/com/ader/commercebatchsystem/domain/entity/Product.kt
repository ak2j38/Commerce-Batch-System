package com.ader.commercebatchsystem.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
class Product (
  @Id
  @Column(name = "product_no")
  val id : Long,
  val productName: String,
  val createdAt: ZonedDateTime? = ZonedDateTime.now(),
  val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
  val deletedAt: ZonedDateTime? = null,
  val sellerNo: Long,
  val category: Int,
  val taxType: String? = "TAX", //TAX , FREE , ZERO
  val sellPrice: BigDecimal? = BigDecimal.ZERO,
  val supplyPrice: BigDecimal? = BigDecimal.ZERO,
  val isActive: Boolean? = true,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Product

    if (id != other.id) return false
    if (productName != other.productName) return false
    if (createdAt != other.createdAt) return false
    if (updatedAt != other.updatedAt) return false
    if (deletedAt != other.deletedAt) return false
    if (sellerNo != other.sellerNo) return false
    if (category != other.category) return false
    if (taxType != other.taxType) return false
    if (sellPrice != other.sellPrice) return false
    if (supplyPrice != other.supplyPrice) return false
    if (isActive != other.isActive) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + productName.hashCode()
    result = 31 * result + (createdAt?.hashCode() ?: 0)
    result = 31 * result + (updatedAt?.hashCode() ?: 0)
    result = 31 * result + (deletedAt?.hashCode() ?: 0)
    result = 31 * result + sellerNo.hashCode()
    result = 31 * result + category
    result = 31 * result + (taxType?.hashCode() ?: 0)
    result = 31 * result + (sellPrice?.hashCode() ?: 0)
    result = 31 * result + (supplyPrice?.hashCode() ?: 0)
    result = 31 * result + (isActive?.hashCode() ?: 0)
    return result
  }
}
