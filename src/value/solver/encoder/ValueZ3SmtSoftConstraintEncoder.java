package value.solver.encoder;

import checkers.inference.InferenceMain;
import checkers.inference.model.ArithmeticConstraint;
import checkers.inference.model.CombineConstraint;
import checkers.inference.model.ComparableConstraint;
import checkers.inference.model.ComparisonConstraint;
import checkers.inference.model.Constraint;
import checkers.inference.model.EqualityConstraint;
import checkers.inference.model.ExistentialConstraint;
import checkers.inference.model.ImplicationConstraint;
import checkers.inference.model.InequalityConstraint;
import checkers.inference.model.PreferenceConstraint;
import checkers.inference.model.Slot;
import checkers.inference.model.SubtypeConstraint;
import checkers.inference.solver.backend.z3smt.Z3SmtFormatTranslator;
import checkers.inference.solver.backend.z3smt.encoder.Z3SmtSoftConstraintEncoder;
import checkers.inference.solver.frontend.Lattice;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import value.representation.TypeCheckValue;
import value.solver.representation.Z3InferenceValue;

public class ValueZ3SmtSoftConstraintEncoder
        extends Z3SmtSoftConstraintEncoder<Z3InferenceValue, TypeCheckValue> {

    public ValueZ3SmtSoftConstraintEncoder(
            Lattice lattice,
            Context ctx,
            Z3SmtFormatTranslator<Z3InferenceValue, TypeCheckValue> z3SmtFormatTranslator) {
        super(lattice, ctx, z3SmtFormatTranslator);
    }

    @Override
    protected void encodeSoftSubtypeConstraint(SubtypeConstraint stc) {
        Constraint eqc =
                InferenceMain.getInstance()
                        .getConstraintManager()
                        .createEqualityConstraint(stc.getSubtype(), stc.getSupertype());

        Expr simplifiedEQC = eqc.serialize(formatTranslator).simplify();

        if (!simplifiedEQC.isTrue()) {
            if (stc.getSubtype().getKind() == Slot.Kind.CONSTANT) {
                addSoftConstraint(simplifiedEQC, 3);
            } else if (stc.getSupertype().getKind() == Slot.Kind.CONSTANT) {
                addSoftConstraint(simplifiedEQC, 2);
            } else {
                addSoftConstraint(simplifiedEQC, 1);
            }
        }
        
        Z3InferenceValue sub = stc.getFirst().serialize(formatTranslator);
        Z3InferenceValue sup = stc.getSecond().serialize(formatTranslator);
        
        // Prefer same upper bound
        BoolExpr lowerBoundEq = ctx.mkAnd(sub.getIntRange(), sup.getIntRange(), ctx.mkEq(sub.getIntRangeLower(), sup.getIntRangeLower()));
        if (!lowerBoundEq.isTrue()) {
	        addSoftConstraint(lowerBoundEq, 1);
	    }
        // Prefer same lower bound
        BoolExpr upperBoundEq = ctx.mkAnd(sub.getIntRange(), sup.getIntRange(), ctx.mkEq(sub.getIntRangeUpper(), sup.getIntRangeUpper()));
        if (!upperBoundEq.isTrue()) {
        	addSoftConstraint(upperBoundEq, 1);
        }
    }
    
	@Override
	protected void encodeSoftComparisonConstraint(ComparisonConstraint cc) {
		switch(cc.getOperation()) {
            case EQUAL_TO:
            	Constraint cst = InferenceMain.getInstance()
                        .getConstraintManager()
                        .createEqualityConstraint(cc.getResult(), cc.getRight());
            	Expr simplified = cst.serialize(formatTranslator).simplify();
                if (!simplified.isTrue()) {
                    addSoftConstraint(simplified, 15);
                }
            default:
            	break;
		}
	}

    @Override
    protected void encodeSoftComparableConstraint(ComparableConstraint cc) {
    	Constraint eqc =
                InferenceMain.getInstance()
                        .getConstraintManager()
                        .createEqualityConstraint(cc.getFirst(), cc.getSecond());

        Expr simplifiedEQC = eqc.serialize(formatTranslator).simplify();
        if (!simplifiedEQC.isTrue()) {
            addSoftConstraint(simplifiedEQC, 1);
        }
    }

    @Override
    protected void encodeSoftArithmeticConstraint(ArithmeticConstraint ac) {
    	Z3InferenceValue left = ac.getLeftOperand().serialize(formatTranslator);
        Z3InferenceValue right = ac.getRightOperand().serialize(formatTranslator);
        Z3InferenceValue res = ac.getResult().serialize(formatTranslator);
        BoolExpr constraint = ctx.mkImplies(ctx.mkAnd(left.getIntRange(), right.getIntRange()), res.getIntRange());
        if (!constraint.isTrue()) {
        	addSoftConstraint(constraint, 1);
        }
    }

    @Override
    protected void encodeSoftEqualityConstraint(EqualityConstraint constraint) {}

    @Override
    protected void encodeSoftInequalityConstraint(InequalityConstraint constraint) {}

    @Override
    protected void encodeSoftImplicationConstraint(ImplicationConstraint constraint) {}

    @Override
    protected void encodeSoftExistentialConstraint(ExistentialConstraint constraint) {}

    @Override
    protected void encodeSoftCombineConstraint(CombineConstraint constraint) {}

    @Override
    protected void encodeSoftPreferenceConstraint(PreferenceConstraint constraint) {}
}
