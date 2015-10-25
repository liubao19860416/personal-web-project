package collection;

import java.util.Iterator;
import java.util.Stack;

/**
 * 使用push方法,实现栈方式存储数据
 * 
 * @author Liubao
 * @2015年5月12日
 * 
 */
public class StackListIternator implements Iterator {

    private static final String Iterator = null;
    private Stack<Iterator> stack;

    public StackListIternator() {
        super();
    }

    /**
     * 使用push方法,实现栈方式存储数据
     * 
     * @param iterator
     */
    public StackListIternator(Iterator iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            Iterator iterator = stack.peek();
            if (iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = stack.peek();
            Object next = iterator.next();
            if (next instanceof Collection) {
                java.util.Iterator iterator2 = (Iterator) ((Collection) next)
                        .iterator();
                stack.push(iterator2);
                return iterator2;
            } else {
                return next;

            }
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("该操作不支持!!!");
    }

}
