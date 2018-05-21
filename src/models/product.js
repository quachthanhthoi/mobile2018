import bookshelf from '../database_config';
import Picture from './picture';
const TABLE_NAME = 'm_products';

/**
 * Product model.
 */
/**
 * @swagger
 * definitions:
 *   Product:
 *     properties:
 *       id:
 *         type: integer
 *       name:
 *         type: string
 *       info:
 *         type: string
 *       price:
 *         type: integer
 *       type:
 *         type: integer
 *       creatorId:
 *         type: integer
 *       categoryId:
 *         type: integer
 *       genreId:
 *         type: integer
 *       sale:
 *         type: integer
 *       createdAt:
 *         type: string
 *       updatedAt:
 *         type: string
 */
class Product extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }
  picture() {
    return this.hasMany(Picture, 'product_id');
  }
}

export default Product;