package uz.brb.java26.enums;

public enum ActionType {

    // CRUD
    CREATE,
    UPDATE,
    DELETE,
    FOUND,
    UNFOUND,

    // 🔐 Auth
    LOGIN_SUCCESS,
    LOGIN_FAILED,
    LOGOUT,

    // 🔑 Security
    PASSWORD_CHANGED,
    ACCESS_DENIED,

    // 📊 Business
    APPROVED,
    REJECTED,
    CANCELLED,

    // ⚙️ System
    SYSTEM_ERROR,
    CONFIG_CHANGED
}