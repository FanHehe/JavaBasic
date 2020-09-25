package Protocol;

// https://blog.csdn.net/ThinkWon/article/details/103592572

public class Tcp {
    public static void main(String[] args) {
        // - 协议格式
        //  - 源端口/目的端口
        //  - 序号
        //  - ACK序号
        //  - [header长度(4)/保留(4)/标志(8)]/ 窗口大小
        //      - CWR
        //      - ECE
        //      - URG
        //      - ACK
        //      - PSH
        //      - RST
        //      - SYN
        //      - FIN
        //  - 校验和/紧急指针
        //  - 自定义首部
        // - 粘包
        //  - 选项
        //
        // - 状态
        //  - 主动发起端
        //      - CLOSED -> SYN_SEND -> ESTABLISHED
        //  - 被动连接端
        //      - CLOSED -> LISTEN -> SYN_RECEIVE -> ESTABLISHED
        //  - 主动断开端
        //      - ESTABLISHED -> FIN_WAIT_1 -> CLOSING -> TIME_WAIT -> CLOSED
        //      - ESTABLISHED -> FIN_WAIT_1 -> FIN_WAIT_2 -> TIME_WAIT -> CLOSED
        //      - ESTABLISHED -> TIME_WAIT  -> CLOSED
        //  - 被动断开端
        //      - ESTABLISHED -> CLOSE_WAIT -> LAST_ACK -> CLOSED
        //  - 这里TIME_WAIT状态持续的时间是2MSL（MSL是任何IP数据报能够在因特网中存活的最长时间），足以让这两个方向上的数据包被丢弃（最长是2MSL）
    }
}


