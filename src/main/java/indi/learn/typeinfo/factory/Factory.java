package indi.learn.typeinfo.factory;

public interface Factory<T> {
	// The generic parameter T allows create( ) to return a different type for each implementation
	//	of Factory,This also makes use of covariant return types.
	T create();
}
