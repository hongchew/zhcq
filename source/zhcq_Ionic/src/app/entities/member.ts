
import { SaleTransaction } from './saletransaction';
import { WishList } from './wishlist';
import { ShoppingCart } from './cart';


export class Member
{

    memberId: number;
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    loyaltyPoints: number;
    salt: string;
    wishList: WishList;
    saleTransactions: SaleTransaction[];
    shoppingCart: ShoppingCart;
    email: string;

	constructor(memberId?: number, firstName?: string, lastName?: string, username?: string, password?: string, loyaltyPoints?: number, email?: string, salt?:string, wishList?: WishList, saleTransactions?: SaleTransaction[], shoppingCart?:ShoppingCart)
	{
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.loyaltyPoints = loyaltyPoints;
        this.salt = salt;
        this.wishList= wishList;
        this.saleTransactions = saleTransactions;
        this.shoppingCart = shoppingCart;
        this.email = email;
    }
}