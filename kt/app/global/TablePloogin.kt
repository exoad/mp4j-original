package app.global

internal interface Tables {
    companion object {
        val t_multiple = floatArrayOf(65536.0f, 52015.957f, 41285.094f, 32768.0f, 26007.979f, 20642.547f, 16384.0f,
                13003.989f, 10321.273f, 8192.0f, 6501.9946f, 5160.6367f, 4096.0f, 3250.9973f, 2580.3184f, 2048.0f, 1625.4987f,
                1290.1592f, 1024.0f, 812.7493f, 645.0796f, 512.0f, 406.37466f, 322.5398f, 256.0f, 203.18733f, 161.2699f, 128.0f,
                101.593666f, 80.63495f, 64.0f, 50.796833f, 40.317474f, 32.0f, 25.398417f, 20.158737f, 16.0f, 12.699208f,
                10.079369f, 8.0f, 6.349604f, 5.0396843f, 4.0f, 3.174802f, 2.5198421f, 2.0f, 1.587401f, 1.2599211f, 1.0f,
                0.7937005f, 0.62996054f, 0.5f, 0.39685026f, 0.31498027f, 0.25f, 0.19842513f, 0.15749013f, 0.125f, 0.099212565f,
                0.07874507f, 0.0625f, 0.049606282f, 0.039372534f, 1.0E-20f)
        val t_c = floatArrayOf(1.3333334f, 1.6f, 1.1428572f, 1.7777778f, 1.0666667f, 1.032258f, 1.0158731f, 1.007874f,
                1.0039216f, 1.0019569f, 1.0009775f, 1.0004885f, 1.0002443f, 1.0001221f, 1.000061f, 1.0000305f, 1.0000153f)
        val t_d = floatArrayOf(0.6666667f, 0.8f, 0.2857143f, 0.8888889f, 0.13333334f, 0.06451613f, 0.031746034f,
                0.015748031f, 0.007843138f, 0.0039138943f, 0.0019550342f, 9.770396E-4f, 4.884005E-4f, 2.4417043E-4f,
                1.2207776E-4f, 6.1037026E-5f, 3.0518047E-5f)
        val t_finv_alloc = floatArrayOf(0.6666667f, 0.4f, 0.2857143f, 0.22222222f, 0.13333334f, 0.06451613f,
                0.031746034f, 0.015748031f, 0.007843138f, 0.0039138943f, 0.0019550342f, 9.770396E-4f, 4.884005E-4f, 2.4417043E-4f,
                1.2207776E-4f, 6.103702E-5f, 3.0518044E-5f)
        val t_alloc = intArrayOf(2, 4, 4, 8, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768)
        val p_0 = byteArrayOf(0)
        val a_0 = byteArrayOf(0)
        val p_2 = byteArrayOf(0, 0, 1, 16)
        val a_2 = byteArrayOf(0, -5, -7, 16)
        val p_3a = byteArrayOf(0, 0, 1, 3, 4, 5, 6, 7)
        val a_3a = byteArrayOf(0, -5, -7, -10, 4, 5, 6, 7)
        val p_3b = byteArrayOf(0, 0, 1, 2, 3, 4, 5, 16)
        val a_3b = byteArrayOf(0, -5, -7, 3, -10, 4, 5, 16)
        val p_4a = byteArrayOf(0, 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        val a_4a = byteArrayOf(0, -5, -7, -10, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        val p_4b = byteArrayOf(0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16)
        val a_4b = byteArrayOf(0, -5, -7, 3, -10, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16)
        val p_4c = byteArrayOf(0, 0, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val a_4c = byteArrayOf(0, -5, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val p_4d = byteArrayOf(0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
        val a_4d = byteArrayOf(0, -5, -7, 3, -10, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
        val p_2a = byteArrayOf(0, 0, 1, 3)
        val a_2a = byteArrayOf(0, -5, -7, -10)
        val t_bal_00 = byteArrayOf(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 0, 0,
                0, 0, 0)
        val t_bal_01 = arrayOf(p_4c, p_4c, p_4c, p_4b, p_4b, p_4b, p_4b, p_4b, p_4b, p_4b, p_4b, p_3b, p_3b, p_3b,
                p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_2, p_2, p_2, p_2, p_0, p_0, p_0, p_0, p_0)
        val t_bal_02 = arrayOf(a_4c, a_4c, a_4c, a_4b, a_4b, a_4b, a_4b, a_4b, a_4b, a_4b, a_4b, a_3b, a_3b, a_3b,
                a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_2, a_2, a_2, a_2, a_0, a_0, a_0, a_0, a_0)
        val t_bal_10 = byteArrayOf(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                2, 0, 0)
        val t_bal_11 = arrayOf(p_4c, p_4c, p_4c, p_4b, p_4b, p_4b, p_4b, p_4b, p_4b, p_4b, p_4b, p_3b, p_3b, p_3b,
                p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_3b, p_2, p_2, p_2, p_2, p_2, p_2, p_2, p_0, p_0)
        val t_bal_12 = arrayOf(a_4c, a_4c, a_4c, a_4b, a_4b, a_4b, a_4b, a_4b, a_4b, a_4b, a_4b, a_3b, a_3b, a_3b,
                a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_3b, a_2, a_2, a_2, a_2, a_2, a_2, a_2, a_0, a_0)
        val t_bal_20 = byteArrayOf(4, 4, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0)
        val t_bal_21 = arrayOf(p_4a, p_4a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_0, p_0, p_0, p_0, p_0, p_0, p_0,
                p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0)
        val t_bal_22 = arrayOf(a_4a, a_4a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_0, a_0, a_0, a_0, a_0, a_0, a_0,
                a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0)
        val t_bal_30 = byteArrayOf(4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0)
        val t_bal_31 = arrayOf(p_4a, p_4a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_0, p_0,
                p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0, p_0)
        val t_bal_32 = arrayOf(a_4a, a_4a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_0, a_0,
                a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0, a_0)
        val t_bal_40 = byteArrayOf(4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 0, 0)
        val t_bal_41 = arrayOf(p_4d, p_4d, p_4d, p_4d, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_3a, p_2a, p_2a, p_2a,
                p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_2a, p_0, p_0)
        val t_bal_42 = arrayOf(a_4d, a_4d, a_4d, a_4d, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_3a, a_2a, a_2a, a_2a,
                a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_2a, a_0, a_0)
    }
}