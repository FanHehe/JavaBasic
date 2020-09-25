
/**
 * 默认model
 */
interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findFirstByUserNickEquals(String userNick);
}
