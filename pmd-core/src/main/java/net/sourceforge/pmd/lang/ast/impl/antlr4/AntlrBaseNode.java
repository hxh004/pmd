/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ast.impl.antlr4;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import net.sourceforge.pmd.util.DataMap;
import net.sourceforge.pmd.util.DataMap.DataKey;

public abstract class AntlrBaseNode<I extends AntlrBaseNode<I>> extends ParserRuleContext implements AntlrNode {

    private final DataMap<DataKey<?, ?>> userData = DataMap.newDataMap();

    /**
     * Constructor required by {@link ParserRuleContext}
     */
    protected AntlrBaseNode() {
        // Nothing to be done
    }

    /**
     * Constructor required by {@link ParserRuleContext}
     *
     * @param parent The parent
     * @param invokingStateNumber the invokingState defined by {@link org.antlr.v4.runtime.RuleContext} parent
     */
    protected AntlrBaseNode(final ParserRuleContext parent, final int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    /**
     * TODO @NoAttribute (port swift rules)
     */
    @Override
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public String getText() {
        return super.getText();
    }


    // FIXME these coordinates are not accurate

    @Override
    public int getBeginLine() {
        return start.getLine(); // This goes from 1 to n
    }

    @Override
    public int getEndLine() {
        return stop.getLine(); // This goes from 1 to n
    }

    @Override
    public int getBeginColumn() {
        return start.getCharPositionInLine(); // This goes from 0 to (n - 1)
    }

    @Override
    public int getEndColumn() {
        return stop.getCharPositionInLine(); // This goes from 0 to (n - 1)
    }

    @Override
    public DataMap<DataKey<?, ?>> getUserMap() {
        return userData;
    }

    @Override
    public I getChild(int i) {
        return cast(super.getChild(i));
    }

    @Override
    public I getParent() {
        return cast(super.getParent());
    }

    protected abstract I cast(ParseTree o);

    @Override
    public int getNumChildren() {
        return getChildCount();
    }

    @Override
    public String getXPathNodeName() {
        final String simpleName = getClass().getSimpleName();
        return simpleName.substring(0, simpleName.length() - "Context".length());
    }
}
