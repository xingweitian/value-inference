package value.solver.z3smt.encoder;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import checkers.inference.model.ConstantSlot;
import checkers.inference.model.Slot;
import checkers.inference.model.VariableSlot;
import checkers.inference.solver.backend.encoder.binary.SubtypeConstraintEncoder;
import checkers.inference.solver.frontend.Lattice;
import value.solver.z3smt.ValueZ3SmtFormatTranslator;
import value.solver.z3smt.representation.Z3InferenceValue;

public class ValueZ3SmtSubtypeConstraintEncoder extends ValueZ3SmtAbstractConstraintEncoder 
		implements SubtypeConstraintEncoder<BoolExpr> {

	public ValueZ3SmtSubtypeConstraintEncoder(Lattice lattice, Context ctx,
			ValueZ3SmtFormatTranslator z3SmtFormatTranslator) {
		super(lattice, ctx, z3SmtFormatTranslator);
	}
	
	protected BoolExpr encode(Slot subtype, Slot supertype) {
        Z3InferenceValue subT = subtype.serialize(z3SmtFormatTranslator);
        Z3InferenceValue superT = supertype.serialize(z3SmtFormatTranslator);

        return valueZ3SmtEncoderUtils.subtype(ctx, subT, superT);
	}

	@Override
	public BoolExpr encodeVariable_Variable(VariableSlot fst, VariableSlot snd) {
		return encode(fst, snd);
	}

	@Override
	public BoolExpr encodeVariable_Constant(VariableSlot fst, ConstantSlot snd) {
		return encode(fst, snd);
	}

	@Override
	public BoolExpr encodeConstant_Variable(ConstantSlot fst, VariableSlot snd) {
		return encode(fst, snd);
	}

}