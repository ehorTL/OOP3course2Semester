import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.RingBuffer;
import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

public class LongEventMain {

	private static final int BUFFER_size = 1024;

	public static void main(String[] args) throws Exception {
		LongEventFactory factory = new LongEventFactory();

		Disruptor<LongEvent> disruptor = new Disruptor(factory, BUFFER_size, new BasicExecutor((ThreadFactory) Thread::new));
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.start();

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		ByteBuffer bb = ByteBuffer.allocate(8);

		for (long l = 0; true; l++) {
			bb.putLong(0, l);
			producer.onData(bb);
			Thread.sleep(1000);
		}
	}
}