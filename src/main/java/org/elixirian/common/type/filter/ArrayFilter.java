/**
 * 
 */
package org.elixirian.common.type.filter;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _____________    ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /   \  /   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /     \/   /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ ___    ____
 *    /   \/    / /      \   \/   /_    _//   \  /   /
 *   /        /  /    ___/\      / /   / /     \/   /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-12-07)
 * @param <T>
 *            input
 * @param <C>
 *            condition
 * @param <R>
 *            result
 */
public interface ArrayFilter<T, C, R> extends Filter1<T[], C, R>
{
	@Override
	R filter(C condition, T[] source);
}
