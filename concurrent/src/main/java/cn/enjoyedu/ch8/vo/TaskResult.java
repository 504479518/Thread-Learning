package cn.enjoyedu.ch8.vo;

/**
 * 类说明：任务处理后返回的结果实体类
 */
public class TaskResult<R> {

    /**
     * 方法执行结果
     */
    private final TaskResultType resultType;
    /**
     * 方法执行后的结果数据
     */
    private final R returnValue;
    /**
     * 如果方法失败，这里可以填充原因
     */
    private final String reason;

    public TaskResult(TaskResultType resultType, R returnValue, String reason) {
        super();
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = reason;
    }

    public TaskResult(TaskResultType resultType, R returnValue) {
        super();
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = "Success";
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public String getReason() {
        return reason;
    }

    public R getReturnValue() {
        return returnValue;
    }

    @Override
    public String toString() {
        return "TaskResult [resultType=" + resultType
                + ", returnValue=" + returnValue
                + ", reason=" + reason + "]";
    }

}
