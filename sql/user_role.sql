CREATE TABLE user_role (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    role_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id)
      REFERENCES user(user_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (role_id)
      REFERENCES role(id)
) ;


INSERT INTO `user_role`(`id`,`role_id`,`user_id`) VALUES (1,1,'3e5ad0fb-6a97-4889-bffb-8267aa0ae1cc');
INSERT INTO `user_role`(`id`,`role_id`,`user_id`) VALUES (2,3,'3e5ad0fb-6a97-4889-bffb-8267aa0ae1cc');
INSERT INTO `user_role`(`id`,`role_id`,`user_id`) VALUES (3,2,'d100d74e-b15f-4a51-bdd5-2b60f71df7a4');
INSERT INTO `user_role`(`id`,`role_id`,`user_id`) VALUES (4,3,'d100d74e-b15f-4a51-bdd5-2b60f71df7a4');