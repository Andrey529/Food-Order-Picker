package model

import java.util.UUID
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class) val userId: UUID,
    @Serializable(with = UUIDSerializer::class) val orderId: UUID?,
)