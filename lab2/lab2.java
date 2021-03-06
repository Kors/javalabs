 /**
  * Коллекция-множество хранит данные в массиве, используя для адреса записи значение хеш-кода объекта
  * В Set не может храниться два одинаковых объекта.
  */
 public interface ISet {
	 
     /**
      * Добавить элемент 
     * @param e
      */
     void add(Object e);
     
     
     /**
      * Удалить элемент 
      */
     boolean remove(Object o);
     
     
     /**
     * Возвращает true, если элемент содержится в коллекции
      */
     boolean contains(Object o);
 }
