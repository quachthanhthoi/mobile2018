import bookshelf from '../database_config';

const TABLE_NAME = 'm_users';

/**
 * User model.
 */
/**
 * @swagger
 * definitions:
 *   User:
 *     properties:
 *       id:
 *         type: integer
 *       username:
 *         type: string
 *       password:
 *         type: string
 *       ava:
 *         type: string
 *       fullname:
 *         type: string
 *       dob:
 *         type: string
 *       email:
 *         type: string
 *       phone:
 *         type: string
 *       sex:
 *         type: integer
 *       firstLogin:
 *         type: integer
 *       type:
 *         type: string
 *       thirdPartyId:
 *         type: string
 *       createdAt:
 *         type: string
 *       updatedAt:
 *         type: string
 */
class User extends bookshelf.Model {

  get tableName() {
    return TABLE_NAME;
  }

  get hasTimestamps() {
    return false;
  }

}

export default User;