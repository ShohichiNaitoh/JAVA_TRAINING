package ch19.ex19_01;

/**
 * Object型のフィールドを保持するリスト。<br>
 * 次のLinekdList要素を指定することで複数の要素をリンクさせることができる。
 * @author naito
 */
public class LinkedList {

	private Object object = null;
	private LinkedList next = null;

	/**
	 * 紐付けるオブジェクトを指定して<code>LinkedList</code>を生成する。<br>
	 * 生成された<code>LinkedList</code>の次の要素はnullとなる。<br>
	 * 生成後に次の<code>LinkedList</code>要素を設定する場合には、{@link LinkedList#setNext(LinkedList next) setNext(LinkedList next)}を使用する。
	 * @param object このLinkedListに紐付けるオブジェクト
	 */
	public LinkedList(Object object){
		this.object = object;
		this.next = null;
	}

	/**
	 * 紐付けるオブジェクトと次の要素を指定して<code>LinkedList</code>を生成する。
	 * @param object この<code>LinkedList</code>に紐付けるオブジェクト
	 * @param next 次の要素となる<code>LinkedList</code>
	 */
	public LinkedList(Object object , LinkedList next){
		this.object = object;
		this.next = next;
	}

	/**
	 * 呼び出した<code>LinkedList</code>に紐付いたオブジェクトを取得する。
	 * @return <code>LinkedList</code>に紐付いたオブジェクト
	 */
	public Object getObject(){
		return object;
	}

	/**
	 * <code>LinkedList</code>に対してオブジェクトを紐付ける。
	 * @param object 紐付けるオブジェクト
	 */
	public void setObject(Object object){
		this.object = object;
	}

	/**
	 * 呼び出した<code>LinkedList</code>の次の要素を取得する。
	 * @return 次の要素
	 */
	public LinkedList getNext(){
		return next;
	}

	/**
	 * 次の要素を設定する。
	 * @param next 次の要素となる<code>LinkedList</code>
	 */
	public void setNext(LinkedList next){
		this.next = next;
	}

	/**
	 * 呼び出した<code>LinkedList</code>にリンクしている要素の要素数を取得する。
	 * @return 要素数
	 */
	public int getCount(){
		int count = 1;
		if(next != null){
			count += next.getCount();
		}
		return count;
	}

	/**
	 * <code>LinkedList</code>に紐付いたオブジェクトの<code>toString()</code>を呼び出す。<br>
	 * この<code>LinkedList</code>にリンクした要素がある場合には、その要素に紐付いたオブジェクトの<code>toString()</code>も呼び出す。
	 */
	public String toString(){
		String message = "";
		if(object != null){
			message = object.toString() + "\n";
		}
		if(next != null){
			message += next.toString();
		}
		return message;
	}
}
