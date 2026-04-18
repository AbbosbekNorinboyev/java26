package uz.brb.java26.enums;

public enum EntityType {

    // 🔐 Auth / User
    AUTH_USER,
    ROLE,
    PERMISSION,

    // 👤 Business
    CUSTOMER,
    EMPLOYEE,

    // 📦 Product / Order
    PRODUCT,
    CATEGORY,
    ORDER,
    ORDER_ITEM,

    // 💳 Payment
    PAYMENT,
    TRANSACTION,

    // 📄 System
    FILE,
    DOCUMENT,

    // ⚙️ System internal
    SYSTEM,
    CONFIG
}