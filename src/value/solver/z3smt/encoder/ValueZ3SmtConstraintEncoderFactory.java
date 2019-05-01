package value.solver.z3smt.encoder;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import backend.z3smt.encoder.Z3SmtConstraintEncoderFactory;
import checkers.inference.solver.backend.encoder.ArithmeticConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.ComparableConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.EqualityConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.InequalityConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.SubtypeConstraintEncoder;
import checkers.inference.solver.backend.encoder.combine.CombineConstraintEncoder;
import checkers.inference.solver.backend.encoder.existential.ExistentialConstraintEncoder;
import checkers.inference.solver.backend.encoder.implication.ImplicationConstraintEncoder;
import checkers.inference.solver.backend.encoder.preference.PreferenceConstraintEncoder;
import checkers.inference.solver.frontend.Lattice;
import value.representation.TypeCheckValue;
import value.solver.z3smt.ValueZ3SmtFormatTranslator;
import value.solver.z3smt.representation.Z3InferenceValue;

public class ValueZ3SmtConstraintEncoderFactory 
	extends Z3SmtConstraintEncoderFactory<Z3InferenceValue, TypeCheckValue, ValueZ3SmtFormatTranslator> {

	public ValueZ3SmtConstraintEncoderFactory(Lattice lattice, Context ctx,
			ValueZ3SmtFormatTranslator z3SmtFormatTranslator) {
		super(lattice, ctx, z3SmtFormatTranslator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SubtypeConstraintEncoder<BoolExpr> createSubtypeConstraintEncoder() {
		return new ValueZ3SmtSubtypeConstraintEncoder(lattice, ctx, formatTranslator);
	}

	@Override
	public EqualityConstraintEncoder<BoolExpr> createEqualityConstraintEncoder() {
		return new ValueZ3SmtEqualityConstraintEncoder(lattice, ctx, formatTranslator);
	}

	@Override
	public InequalityConstraintEncoder<BoolExpr> createInequalityConstraintEncoder() {
		return null;
	}

	@Override
	public ComparableConstraintEncoder<BoolExpr> createComparableConstraintEncoder() {
		return new ValueZ3SmtComparableConstraintEncoder(lattice, ctx, formatTranslator);
	}

	@Override
	public PreferenceConstraintEncoder<BoolExpr> createPreferenceConstraintEncoder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CombineConstraintEncoder<BoolExpr> createCombineConstraintEncoder() {
		return null;
	}

	@Override
	public ExistentialConstraintEncoder<BoolExpr> createExistentialConstraintEncoder() {
		return null;
	}

	@Override
	public ImplicationConstraintEncoder<BoolExpr> createImplicationConstraintEncoder() {
		return null;
	}

	@Override
	public ArithmeticConstraintEncoder<BoolExpr> createArithmeticConstraintEncoder() {
		return new ValueZ3SmtArithmeticConstraintEncoder(lattice, ctx, formatTranslator);
	}

}
