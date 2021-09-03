package cn.enjoyedu.ch8.assist;

import cn.enjoyedu.ch8.vo.SrcDocVo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * 类说明：生成待处理文档
 */
public class CreatePendingDocs {

    /**
     * 生成一批待处理文档
     *
     * @return 待处理文档列表
     */
    public static List<SrcDocVo> makePendingDoc(int count) {
        Random r = new Random();
        Random rCount = new Random();
        //文档列表
        List<SrcDocVo> docList = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            //文档中题目列表
            List<Integer> questionList = new LinkedList<Integer>();
            int questNum = r.nextInt(60) + Consts.QUESTION_COUNT_IN_DOC;
            for (int j = 0; j < questNum; j++) {
                int questionId = r.nextInt(Consts.SIZE_OF_QUESTION_BANK);
                questionList.add(questionId);
            }
            SrcDocVo pendingDocVo = new SrcDocVo("pending_" + i, questionList);
            docList.add(pendingDocVo);
        }
        return docList;
    }

}
