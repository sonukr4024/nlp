//package com.nlp.nlp.config;
//
//import com.nlp.nlp.utils.CacheUtils;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SessionUtils {
//    private static final String USER_ID = "user_id";
//    private static final String SESSION_ID = "sessionId";
//    private static final String UTC_OFFSET = "utcOffset";
//
//    private static final String PROJECT = "project";
//    private static final String PRODUCT = "product";
//    private static final String ENV = "env";
//    private static final String EMAIL_ID = "email_id";
//
//    private static final String PERMISSIONS = "permissions";
//
//
//    private static final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);
//
//    public static void setSessionId(String sessionId) {
//        setLocalThreadAttribute(SESSION_ID, sessionId);
//    }
//
//    public static String getSessionId() {
//        return (String) getLocalThreadAttribute(SESSION_ID);
//    }
//
//    public static String getUserId() {
//        return (String) getAttribute(USER_ID);
//    }
//
//    public static void setUserId(String userId) {
//        setAttribute(USER_ID, userId);
//    }
//
//    public static String getProject() {
//        return (String) getAttribute(PROJECT);
//    }
//
//    public static void setProject(String project) {
//        setAttribute(PROJECT, project);
//    }
//
//    public static String getProduct() {
//        return (String) getAttribute(PRODUCT);
//    }
//
//    public static void setProduct(String product) {
//        setAttribute(PRODUCT, product);
//    }
//
//    public static String getEnv() {
//        return (String) getAttribute(ENV);
//    }
//
//    public static void setEnv(String env) {
//        setAttribute(ENV, env);
//    }
//
//
//    public static String getEmailId() {
//        return (String) getAttribute(EMAIL_ID);
//    }
//
//    public static void setEmailId(String emailId) {
//        setAttribute(EMAIL_ID, emailId);
//    }
//
//    public static Collection<GrantedAuthority> getPermissions() {
//        return (Collection<GrantedAuthority>) getAttribute(PERMISSIONS);
//    }
//
//    public static void setPermissions(Collection<GrantedAuthority> permissions) {
//        setAttribute(PERMISSIONS, permissions);
//    }
//
//    public static final void setUtcOffset(long utcOffset) {
//        setAttribute(UTC_OFFSET, utcOffset);
//    }
//
//    public static long getUtcOffset() {
//        return (long) getAttribute(UTC_OFFSET);
//    }
//
//    private static final void setAttribute(Object name, Object value) {
//        if (null == value) {
//            CacheUtils.removeSession(getKey(name));
//        } else {
//            CacheUtils.putSession(getKey(name), value);
//        }
//    }
//
//    @NotNull
//    private static String getKey(Object name) {
//        return getSessionId() + "-" + name;
//    }
//
//    private static final Object getAttribute(Object name) {
//        return CacheUtils.getSession(getKey(name));
//    }
//
//    private static final void setLocalThreadAttribute(String name, Object value) {
//        threadLocal.get().put(name, value);
//    }
//
//    private static final Object getLocalThreadAttribute(String name) {
//        return threadLocal.get().get(name);
//    }
//
//    public static void clearLocalThread() {
//        if (threadLocal != null) {
//            threadLocal.remove();
//        }
//    }
//}
