package com.ader.commercebatchsystem.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
class Seller(
  @Id
  @Column(name = "seller_no")
  val id: Long,
  val sellerName: String,
  val businessNo: Int? = 0,
  val sellType: String,
  val createdAt: ZonedDateTime? = ZonedDateTime.now(),
  val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
  val deletedAt: ZonedDateTime? = null,
  val bankType: String? = null,
  val accountNo: Int? = null,
  val accountOwnerName: String? = null,
  val isActive: Boolean? = true,
  val defaultDeliveryAmount: Int? = 3000,
  val commission: Int? = 0,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Seller

    if (id != other.id) return false
    if (sellerName != other.sellerName) return false
    if (businessNo != other.businessNo) return false
    if (sellType != other.sellType) return false
    if (createdAt != other.createdAt) return false
    if (updatedAt != other.updatedAt) return false
    if (deletedAt != other.deletedAt) return false
    if (bankType != other.bankType) return false
    if (accountNo != other.accountNo) return false
    if (accountOwnerName != other.accountOwnerName) return false
    if (isActive != other.isActive) return false
    if (defaultDeliveryAmount != other.defaultDeliveryAmount) return false
    if (commission != other.commission) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + sellerName.hashCode()
    result = 31 * result + (businessNo ?: 0)
    result = 31 * result + sellType.hashCode()
    result = 31 * result + (createdAt?.hashCode() ?: 0)
    result = 31 * result + (updatedAt?.hashCode() ?: 0)
    result = 31 * result + (deletedAt?.hashCode() ?: 0)
    result = 31 * result + (bankType?.hashCode() ?: 0)
    result = 31 * result + (accountNo ?: 0)
    result = 31 * result + (accountOwnerName?.hashCode() ?: 0)
    result = 31 * result + (isActive?.hashCode() ?: 0)
    result = 31 * result + (defaultDeliveryAmount ?: 0)
    result = 31 * result + (commission ?: 0)
    return result
  }
}
