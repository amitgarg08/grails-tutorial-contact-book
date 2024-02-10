package online.contact.book

/**
 * Created by amitg on 2/4/2024.
 */
class GlobalConfig {

    public static final def USER_TYPE = [
            ADMINISTRATOR : "ADMINISTRATOR",
            REGULAR_MEMBER: "REGULAR_MEMBER",
    ]


    public static Integer itemsPerPage() {
        return 5
    }

}
