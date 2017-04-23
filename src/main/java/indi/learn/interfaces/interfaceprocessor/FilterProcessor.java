package indi.learn.interfaces.interfaceprocessor;

import indi.learn.interfaces.filters.BandPass;
import indi.learn.interfaces.filters.Filter;
import indi.learn.interfaces.filters.HighPass;
import indi.learn.interfaces.filters.LowPass;
import indi.learn.interfaces.filters.Waveform;

class FilterAdapter implements Processor {
	Filter filter;

	public FilterAdapter(Filter filter) {
		this.filter = filter;
	}

	public String name() {
		return filter.name();
	}

	public Waveform process(Object input) {
		return filter.process((Waveform) input);
	}
}

public class FilterProcessor {
	public static void main(String[] args) {
		Waveform w = new Waveform();
		Apply.process(new FilterAdapter(new LowPass(1.0)), w);
		Apply.process(new FilterAdapter(new HighPass(2.0)), w);
		Apply.process(new FilterAdapter(new BandPass(3.0, 4.0)), w);
	}
}
