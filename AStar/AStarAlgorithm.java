package AStar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AStarAlgorithm {

    public String plan;
    public String process = "";
    private static final int[][] DIREC = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void findPath(int[][] map, int x1, int y1, int x2, int y2) {
        List<Position> openList = new ArrayList<AStarAlgorithm.Position>();
        List<Position> closeList = new ArrayList<AStarAlgorithm.Position>();
        boolean findFlag = false;
        Position termPos = null;
        String pattern = "(%d,%d)";
        // 起始点
        Position startPos = new Position(x1, y1, calcH(x1, y1, x2, y2));
        openList.add(startPos);
        process += "addOpenList:" + String.format(pattern, startPos.row, startPos.col) + "\n";
        do {
            // 通过在开启列表中找到F值最小的点作为当前点
            Position currentPos = openList.get(0);
            for (int i = 0; i < openList.size(); i++) {
                if (currentPos.F > openList.get(i).F) {
                    currentPos = openList.get(i);
                }
            }
            // 将找到的当前点放到关闭列表中，并从开启列表中删除
            closeList.add(currentPos);
            process += "addCloseList:" + String.format(pattern, currentPos.row, currentPos.col) + "\n";
            openList.remove(currentPos);
            // process += "removeOpenList:" + String.format(pattern, currentPos.row, currentPos.col) + "\n";

            //遍历当前点对应的4个相邻点
            for (int i = 0; i < DIREC.length; i++) {
                int tmpX = currentPos.row + DIREC[i][0];
                int tmpY = currentPos.col + DIREC[i][1];
                if (tmpX < 0 || tmpX >= map.length || tmpY < 0 || tmpY >= map[0].length) {
                    continue;
                }
                //创建对应的点
                Position tmpPos = new Position(tmpX, tmpY, calcH(tmpX, tmpY, x2, y2), currentPos);
                //map中对应的格子中的值为0（障碍）， 或对应的点已经在关闭列表中
                if (map[tmpX][tmpY] == 0 || closeList.contains(tmpPos)) {
                    continue;
                }
                //如果不在开启列表中，则加入到开启列表
                if (!openList.contains(tmpPos)) {
                    openList.add(tmpPos);
                    process += "addOpenList:" + String.format(pattern, tmpPos.row, tmpPos.col) + "\n";
                } else {
                    // 如果已经存在开启列表中，则用G值考察新的路径是否更好，如果该路径更好，则把父节点改成当前格并从新计算FGH
                    Position prePos = null;
                    for (Position pos : openList) {
                        if (pos.row == tmpX && pos.col == tmpY) {
                            prePos = pos;
                            break;
                        }
                    }
                    if (tmpPos.G < prePos.G) {
                        prePos.setFaPos(currentPos);
                    }
                }
            }
            // 判断终点是否在开启列表中
            for (Position tpos : openList) {
                if (tpos.row == x2 && tpos.col == y2) {
                    termPos = tpos;
                    findFlag = true;
                    break;
                }
            }

        } while(openList.size() != 0 && findFlag == false);

        if(!findFlag) {
            plan = null;
        }

        Stack<String> resStack = new Stack<String>();
        if (termPos != null) {
            resStack.push(String.format(pattern, termPos.row, termPos.col));
            while(termPos.fa != null) {
                termPos = termPos.fa;
                resStack.push(String.format(pattern, termPos.row, termPos.col));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!resStack.empty()) {
            sb.append(resStack.pop());
            if (!resStack.empty()) {
                sb.append(" -> ");
            }
        }
        plan = sb.toString();
    }

    public String getPlan(){
        return plan;
    }

    public String getProcess(){
        return process;
    }

    /**
     * 计算某个格子的H值
     * @param x
     * @param y
     * @param tx 终点的x值
     * @param ty 终点的y值
     * @return
     */
    private static int calcH(int x, int y, int tx, int ty) {
        int diff = Math.abs(x - tx) + Math.abs(y - ty);
        return diff * 10;
    }


    static class Position {
        public int F;
        public int G;
        public int H;
        public Position fa;
        public int row;
        public int col;

        public Position() {
        }

        public Position(int row, int col, int H) {
            this(row, col, H, null);
        }

        public Position(int row, int col, int H, Position pos) {
            this.H = H;
            this.row = row;
            this.col = col;
            this.fa = pos;
            this.G = calcG();
            this.F = G + H;
        }

        /** 
         * 计算某个点到起始点的代价G
         * @return
         */
        private int calcG() {
            if (fa == null) {
                return 0;
            }
            return 10 + fa.G;
        } 

        public void setFaPos(Position pos) {
            this.fa = pos;
            this.G = calcG();
            this.F = G + H;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position pos = (Position) obj;
            return this.row == pos.row && this.col == pos.col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }

    }

}
