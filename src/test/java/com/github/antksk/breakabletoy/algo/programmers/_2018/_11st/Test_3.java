package com.github.antksk.breakabletoy.algo.programmers._2018._11st;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static java.lang.Math.max;
import static java.lang.Math.min;

/*
XX 게임에서는 지형 편집 기능을 이용하여 플레이어가 직접 게임 속 지형을 수정할 수 있습니다.
이 게임에서는 1 x 1 x 1 크기의 정육면체 블록을 쌓아 게임 속 지형을 표현합니다. 이때, 블록이 공중에 떠 있거나,
블록 하나가 여러 개의 칸에 걸쳐 놓일 수는 없습니다.
따라서 지형을 편집하기 위해서는 각 칸의 제일 위에 블록 1개를 새로 추가하거나,
제일 위에 있는 블록 한 개를 삭제하는 방식으로 지형을 수정해야 합니다.
이때, 블록 한 개를 새로 추가하거나 삭제하기 위해서는 게임머니를 사용해야 하므로 몇 개의 블록을 추가하고 삭제할지 신중한 선택이 필요합니다.

이 게임을 즐기던 한 플레이어는 N x N 크기의 지역에 자신만의 별장을 만들고 싶어졌습니다.
이를 위해서는 울퉁불퉁한 지형의 모든 칸의 높이가 같아지도록 만들어야 합니다.
이때, 블록 한 개를 추가하려면 P의 비용이, 제거하려면 Q의 비용이 들게 됩니다.
다음은 블록 한 개를 추가할 때 5의 비용이, 제거할 때 3의 비용이 드는 경우,
3 x 3 넓이의 모든 칸의 블록 높이가 같아지도록 만드는 예시입니다.

- 한개의 블록 추가 비용은 P
- 한개의 블럭 제거 비용은 Q

참고 :
- http://coloredrabbit.tistory.com/59
- http://ilyoan.tistory.com/entry/Ternary-Search
- http://javacan.tistory.com/entry/19
- https://ko.wikipedia.org/wiki/%EB%8B%A8%EC%A1%B0%ED%95%A8%EC%88%98
 */
@Slf4j
public class Test_3 {

    public static final int LAND_3D = 3;

    private long heightCalculation(int[][] land, int P, int Q, long offset) {
        final int LAND_LEN = land.length;
        long result = 0;
        for (int i = 0; i< LAND_LEN; i++) {
            for (int j = 0; j < LAND_LEN; j++) {
                result += (land[i][j] - offset) * (land[i][j] - offset > 0 ? Q : -P);
            }
        }
        return result;
    }

    private long calculatioLandInfo(long maxHeight, long low, int land3d) {
        return (low + maxHeight) / land3d;
    }

    private long findMaxHeight(int[][] land) {
        final int LAND_LEN = land.length;

        long maxHeight = 0;

        for(int i=0; i< LAND_LEN;i++) {
            for (int j = 0; j < LAND_LEN; j++) {
                // log.debug("land[{}][{}] = {}", i, j, land[i][j]);
                maxHeight = max(maxHeight, land[i][j]);
            }
        }
        // log.debug("maxHeight = {}",maxHeight);
        return maxHeight;
    }

    public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;
        
        long low = 1;

        long height = findMaxHeight(land);

        // Ternary Search
        // for (int i = 0; i< 64; i++) {
            long highToLow = calculatioLandInfo(height, 2 * low, LAND_3D);
            long lowToHigh = calculatioLandInfo(2 * height, low, LAND_3D);
            long lowBase = heightCalculation(land, P, Q, highToLow);
            long hightBase = heightCalculation(land, P, Q, lowToHigh);

            if (lowBase > hightBase){
                low = highToLow;
            }
            else{
                height = lowToHigh;
            }
        // }
        // log.debug("low = {}, height = {}", low, height);

        long mid = (low + height) / 2;

        for(long i = mid - 10; i <= mid+10;i++)
            answer = min(answer, heightCalculation(land, P, Q, i));

        return answer;
    }




    @Test
    public void test(){
        log.debug("{}", solution(new int[][]{{1, 2}, {2, 3}}, 3, 2));
        log.debug("{}", solution(new int[][]{{4, 4, 3}, {3, 2, 2}, { 2, 1, 0 }}, 5, 3));
    }
}
