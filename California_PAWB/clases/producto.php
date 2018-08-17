<?php
	include_once('conexion.php');
	class Product extends Model{
		public $code;
		public $product;
		public $description;
		public $price;

		public function __construct(){ 
      parent::__construct(); 
    } 

		public function get_products(){ 
      $sql = $this->db->query("SELECT * FROM productos");  
      $html = '';
      foreach ($sql->fetch_all(MYSQLI_ASSOC) as $key){
        $code = "'".$key['id_producto']."'";
        $html .= '<tr>
                    <td>'.$key['id_producto'].'</td>
                    <td>'.$key['nombre'].'</td>
                    <td>'.$key['descripcion'].'</td>
                    <td align="right">'.$key['precio_unitario'].'</td>
                    <td align="right">
                      <input type="number" id="'.$key['id_producto'].'" value="1" min="1">
                    </td>
                    <td>
                      <button onClick="addProduct('.$code.');">
                        Agregar
                      </button>
                    </td>
                  </tr>';
      }
      return $html;
   	} 

 		public function search_code($code){
 			$sql = $this->db->query("SELECT * FROM productos WHERE id_producto = '$code'"); 
      $product = $sql->fetch_all(MYSQLI_ASSOC); 
      $status = 0;
      foreach ($product as $key){
    		$this->code = $key['id_producto'];
    		$this->product = $key['nombre'];
    		$this->description = $key['descripcion'];
    		$this->price = $key['precio_unitario'];
    		$status++;
    	}
    	return $status;
 		}
	}
?>